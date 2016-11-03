package com.puhui.app.service;


import java.util.Map;

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
    
    /**
     * 消息推送
     * 
     * @author lichunyue
     * @param map 消息类型
     * @param pushType
     */
    public void pushUnwrapMessage(Map<String,Object> map, int pushType);
    
	/**
	 * 推送解绑消息
	 * @return
	 * @author lichunyue
	 * @param map
	 * @date 2016年5月9日
	 */
	public void pushUnwrapMessageMethod(Map<String,Object> map);

}
