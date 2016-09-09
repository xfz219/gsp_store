package com.puhui.app.dao;

import com.puhui.app.po.AppCustomerMessage;

/**
 * 客户消息操作
 * @author liuhongyu
 * @date 2015年3月25日
 *
 */
public interface AppCustomerMessageDao extends BaseDao<AppCustomerMessage> {

	/**
     * 插入customer表消息
     * @author lichunyue
     * @return
     */
	public void insertCustomerMessage(AppCustomerMessage appCustomerMessage);
}
