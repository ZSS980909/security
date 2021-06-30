package com.zss98.AuthorityResolving.Utils;

import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.function.Function;

@Component
public class JwtTokenUtils implements Serializable {

    private static String pass = "ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=";

    private static Key key;

    private static JwtBuilder jwtBuilder ;

    private static JwtParser jwtParser;

    private RedisUtils redis;

    public JwtTokenUtils(RedisUtils redis){
        this.redis = redis;
    }

    public static final String AUTHORITIES_KEY = "user";

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    @Value("${jwt.pointLogin}")
    private boolean isPoint;


    @Value("${jwt.prefix}")
    private String prefix;

    static {
       key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtTokenUtils.pass));
       jwtBuilder = Jwts.builder().signWith(key, SignatureAlgorithm.HS512);
       jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }
    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     */
    public String createToken(String name){
        String token = jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .claim(AUTHORITIES_KEY, name)
                .setSubject(name)
                .compact();
        // 将token存入到redis  进行时效管理
        redis.set(prefix+name,token,EXPIRATION);
        return token;
    }

    /**
     * 验证token
     * @param token
     * @param username
     * @return
     */
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        String key = prefix+username;
        if(isPoint) {
            // 如果开启单点登录，则执行token校验
            if (!redis.hasKey(key)) {
                return false;
            } else {
                if (!token.equals(redis.get(key))) {
                    return false;
                }
            }
        }
        return (tokenUsername.equals(username) && isTokenExpired(key)
        );
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String key) {
        // 在redis中查询是否还存在
        if(redis.hasKey(key)){
          // 如果存在 则查询有效期是否低于30min
          if(redis.getExpire(key)<60*30){
              // 低于30min则重新给2小时的有效期
              redis.expire(key,EXPIRATION);
          }
          return true;
        }else{
            return false;
        }
    }


}
