package com.gsp.app.model;

import com.gsp.app.constant.ErrorCode;
import com.gsp.app.utils.JsonUtils;

/**
 * fengzhix.xu on 2018/5/19.
 */
public class Response {
    private boolean suc;
    private int code;
    private Object object;

    private Response(boolean suc, int code, Object object) {
        this.suc = suc;
        this.code = code;
        this.object = object;
    }


    public static <T> String suc(T t) {
        return JsonUtils.objectToJson(new Response(true, ErrorCode.SUC, t));
    }


    public static String fail(int code, String message) {
        return JsonUtils.objectToJson(new Response(false, code, message));
    }


    public boolean isSuc() {
        return suc;
    }

    public void setSuc(boolean suc) {
        this.suc = suc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
