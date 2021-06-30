package com.zss98.AuthorityResolving.Entity;


public enum RespEnum {
    OK(200,"成功"),
    SYS_ERROR(500,"系统繁忙"),
    ERROR(500,"失败"),
    AUTH_FAIL(401,"认证失败"),
    CHECK_FAIL(413,"校验失败"),
    NOT_AUTH(401,"没有权限");
    private int code;
    private String msg;

    RespEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CommonResult result(){
        return new CommonResult(getCode(),getMsg());
    }

    public CommonResult result(Object data){
        return new CommonResult(getCode(),getMsg(),data);
    }
}
