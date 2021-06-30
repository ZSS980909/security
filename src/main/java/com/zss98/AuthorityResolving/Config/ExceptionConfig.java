package com.zss98.AuthorityResolving.Config;

import com.zss98.AuthorityResolving.Entity.CommonResult;
import com.zss98.AuthorityResolving.Entity.RespEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionConfig {
    /**
     * 普通异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult allException(HttpServletRequest request,Exception e){
        e.printStackTrace();
        return RespEnum.SYS_ERROR.result();
    }

    /**
     * 空指针异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public CommonResult nullException(HttpServletRequest request,NullPointerException e){
        e.printStackTrace();
        return RespEnum.SYS_ERROR.result();
    }
}
