package com.gsp.app.model;

import com.gsp.app.constant.ErrorEnum;
import com.gsp.app.utils.JsonUtils;

/**
 * fengzhix.xu on 2018/5/19.
 */
public class Response {
    private int code;
    private Object object;

    private Response(int code, Object object) {
        this.code = code;
        this.object = object;
    }


    public static <T> String suc(T t) {
        return JsonUtils.objectToJson(new Response(ErrorEnum.SUC.getCode(), t));
    }


    public static String fail(ErrorEnum errorEnum) {
        return JsonUtils.objectToJson(new Response(errorEnum.getCode(), errorEnum.getMessage()));
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

    public static void main(String[] args) {
        User user = new User();
        user.setPassWord("xxx");
        user.setUserName("213");
        String suc = Response.suc(user);
        System.out.println(suc);
    }
}
