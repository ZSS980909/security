package com.zss98.AuthorityResolving.Utils;

import com.alibaba.fastjson.JSONObject;
import com.zss98.AuthorityResolving.Entity.CommonResult;
import com.zss98.AuthorityResolving.Entity.RespEnum;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {

    public static void out(HttpServletResponse res, CommonResult result){
        try {
            res.setCharacterEncoding("utf-8");
            res.setContentType("application/json;charset=utf-8");
            res.getWriter().print(JSONObject.toJSONString(result));
            res.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
