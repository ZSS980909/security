package com.zss98.AuthorityResolving.Config;

import com.alibaba.fastjson.JSONObject;
import com.zss98.AuthorityResolving.Entity.RespEnum;
import com.zss98.AuthorityResolving.Utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.out(res,RespEnum.NOT_AUTH.result());
    }
}
