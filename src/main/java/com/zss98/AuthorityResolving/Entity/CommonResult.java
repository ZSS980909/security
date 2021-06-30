package com.zss98.AuthorityResolving.Entity;

import lombok.Data;

@Data
public class CommonResult<T> {
    private int code;
    private String msg;
    private T data;

    public CommonResult(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public CommonResult(int code,String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

}

