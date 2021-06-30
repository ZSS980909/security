package com.zss98.AuthorityResolving.Config;

import com.alibaba.fastjson.JSONObject;
import com.zss98.AuthorityResolving.Entity.RespEnum;
import com.zss98.AuthorityResolving.Utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.out(res,RespEnum.AUTH_FAIL.result());
    }
}
