package com.gsp.app.utils;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * json工具类
 * @author xiaobowen
 *
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private JsonUtils(){}
    
    static{
        //设置默认时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        //序列化null值处理方式
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
            @Override
            public void serialize(Object obj, JsonGenerator jg, SerializerProvider sp) throws IOException,
            JsonProcessingException {
                jg.writeString("");
            }
        });
    }
    
    /**将Java对象转为json字符串
     * @author xiaobowen
     * @param obj
     * @return
     * @throws Exception 
     */
    public static String objectToJson(Object obj) throws Exception{
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
    /**将Java对象转为json字符串
     * @author xiaobowen
     * @param obj
     * @return
     * @throws Exception 
     */
    public static String objectToJson(Object obj , DateFormat df) throws Exception{
        try {
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(df);
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
    /**将Java对象转为json字符串
     * @author xiaobowen
     * @param obj
     * @return
     * @throws Exception 
     */
    public static String objectToJson(Object obj , DateFormat df , JsonSerializer<Object> nvs) throws Exception{
        try {
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(df);
            om.getSerializerProvider().setNullValueSerializer(nvs);
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
    /**将json字符串转为Java对象
     * @author xiaobowen
     * @param json
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T jsonToObject(String json , Class<T> clazz) throws Exception{
        try {
            return objectMapper.readValue(json,clazz);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
    /**将json字符串转为Java对象
     * @author xiaobowen
     * @param json
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T jsonToObject(String json , TypeReference<T> typeReference) throws Exception{
        try {
            return objectMapper.readValue(json,typeReference);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
    /**将json字符串转为Java对象
     * @author xiaobowen
     * @param json
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T jsonToObject(String json , TypeReference<T> typeReference , DateFormat df) throws Exception{
        try {
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(df);
            return om.readValue(json,typeReference);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
}
