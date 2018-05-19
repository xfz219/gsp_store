package com.gsp.app.constant;

/**
 * fengzhix.xu on 2018/5/19.
 */
public enum ErrorEnum {

    PWD_ERROR(301, "用户名密码错误"),
    FAIL(201, "系统错误"),
    SUC(200,"请求成功");
    private int code;
    private String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
