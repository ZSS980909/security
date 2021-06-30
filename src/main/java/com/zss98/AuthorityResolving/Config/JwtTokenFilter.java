package com.zss98.AuthorityResolving.Config;

import com.zss98.AuthorityResolving.Service.UserService;
import com.zss98.AuthorityResolving.Utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtils tokenUtils;

    private UserService service;

    public JwtTokenFilter(JwtTokenUtils tokenUtils,UserService service){
        this.tokenUtils = tokenUtils;
        this.service = service;
    }

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = req.getHeader(this.tokenHeader);  // 从header 中获取token
        String username = null;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {  // token 以 Bearer 为前缀，表示 Bearer Token ，区别于MAC Token
            authToken = requestHeader.substring(7);
            try {
                username = tokenUtils.getUsernameFromToken(authToken);  // 从token中解析出 username
            } catch (ExpiredJwtException e) {
                System.out.println("无法解析");
            }
        }
        // 验证token
        if(username != null && tokenUtils.validateToken(authToken,username) ){
            UserDetails user = service.loadUserByUsername(username);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities()));  // 在上下文中记录UserDetails
        }
        filterChain.doFilter(req,res);
    }
}
