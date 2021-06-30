package com.zss98.AuthorityResolving.Controller;

import com.zss98.AuthorityResolving.Entity.CommonResult;
import com.zss98.AuthorityResolving.Entity.RequestDTO;
import com.zss98.AuthorityResolving.Entity.RespEnum;
import com.zss98.AuthorityResolving.Entity.UserData;
import com.zss98.AuthorityResolving.Service.UserService;
import com.zss98.AuthorityResolving.Utils.JwtTokenUtils;
import com.zss98.AuthorityResolving.Utils.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private UserService service;
    private JwtTokenUtils tokenUtils;
    public AuthController (UserService service,JwtTokenUtils utils){
        this.service = service;
        this.tokenUtils = utils;
    }

    @RequestMapping("/login")
    public CommonResult userLogin(@RequestBody RequestDTO request){
        String user = request.getUserName();
        String pass = request.getPassword();
        if(user.isEmpty()){
            return RespEnum.CHECK_FAIL.result("用户不能为空");
        }
        if(pass.isEmpty()){
            return RespEnum.CHECK_FAIL.result("密码不能为空");
        }
        UserDetails userInfo = service.loadUserByUsername(user);
        if(userInfo==null){
            return RespEnum.CHECK_FAIL.result("用户不存在");
        }
        if(!passwordEncoder.matches(pass,userInfo.getPassword())){
            return RespEnum.CHECK_FAIL.result("密码错误");
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo,"",userInfo.getAuthorities()));
        String token = tokenUtils.createToken(user);
        return RespEnum.OK.result(token);
    }

    @RequestMapping("/getAll")
    public CommonResult getAll(){
        return RespEnum.OK.result(service.getAllUser());
    }
}
