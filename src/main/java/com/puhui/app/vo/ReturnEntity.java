package com.puhui.app.vo;

import java.io.Serializable;

public class ReturnEntity<T> implements Serializable{
    private static final long serialVersionUID = -8276046228949065227L;
    private boolean success;//是否成功
    private String msg;//描述
    private T obj;//数据
    
    public ReturnEntity(){}
    
    public ReturnEntity(boolean success , String msg){
        this.success = success;
        this.msg = msg;
    }
    
    public ReturnEntity(boolean success , String msg , T obj){
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }
    
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getObj() {
        return obj;
    }
    public void setObj(T obj) {
        this.obj = obj;
    }
}
