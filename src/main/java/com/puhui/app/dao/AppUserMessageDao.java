package com.puhui.app.dao;


import com.puhui.app.po.AppUserMessage;

/**
 * 员工消息操作
 * @author yupeng
 * @date 2015年3月11日
 *
 */
public interface AppUserMessageDao extends BaseDao<AppUserMessage> {

	/**
	 * 插入appUserMessage表数据
	 * @return
	 * @author lichunyue
	 * @date 2015年4月8日
	 */
	public void insertUserMessage(AppUserMessage appUserMessage);
	
}
