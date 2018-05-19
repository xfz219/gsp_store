package com.gsp.app.model;

import com.alibaba.fastjson.JSONObject;
import com.gsp.app.constant.ErrorEnum;
import com.gsp.app.utils.JsonUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * fengzhix.xu on 2018/5/19.
 */
@NoArgsConstructor
@Data
public class Response {
    private int code;
    private Object result;

    private Response(int code, Object object) {
        this.code = code;
        this.result = object;
    }
    

    public static <T> String suc(T t) {
        return JSONObject.toJSONString(new Response(ErrorEnum.SUC.getCode(), t));
    }


    public static String fail(ErrorEnum errorEnum) {
        return JSONObject.toJSONString(new Response(errorEnum.getCode(), errorEnum.getMessage()));
    }
}
