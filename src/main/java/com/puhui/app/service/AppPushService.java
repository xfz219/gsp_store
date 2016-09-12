package com.puhui.app.service;


import com.puhui.app.vo.AppPushMessageVo;

public interface AppPushService {

    /**
     * 消息推送门店
     * 
     * @author lichunyue
     * @param appPushMessageVo 消息类型
     */
    public void pushMessageUser(AppPushMessageVo appPushMessageVo);
    
    /**
     * 消息推送个人
     * 
     * @author lichunyue
     * @param appPushMessageVo 消息类型
     */
    public void pushMessageCustomer(AppPushMessageVo appPushMessageVo);

}
