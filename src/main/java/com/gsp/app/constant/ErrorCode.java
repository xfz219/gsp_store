package com.gsp.app.constant;

/**
 * fengzhix.xu on 2018/5/19.
 */
public interface ErrorCode {

    int SUC = 200;

    int FAIL = 201;

    /**
     * 登录的错误码
     */
    interface LoginErrorCode {
        int PWD_ERROR = 301;
    }
}
