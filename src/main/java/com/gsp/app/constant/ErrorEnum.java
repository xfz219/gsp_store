package com.gsp.app.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * fengzhix.xu on 2018/5/19.
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    OPERATION_ERROR(304,"操作失败"),
    PARAM_EMPTY(303,"参数不能为空"),
    USER_EMPTY(302, "用户或密码不能为空"),
    PWD_ERROR(301, "用户名密码错误"),
    FAIL(201, "系统错误"),
    SUC(200, "请求成功");
    private int code;
    private String message;

}
