package com.puhui.app.po;

import java.util.Date;

import com.puhui.app.po.BaseBean;

/**
 * 员工消息
 * @author yupeng
 * @date 2015年3月11日
 *
 */
public class AppUserMessage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 员工编号
	 */
	private Integer sellerNumber;
	
	/**
	 * 消息内容
	 */
	private String context;
	
	/**
	 * 是否已读(0:未读；1:已读)
	 */
	private Integer isRead;
	
	/**
	 * 消息类型
	 */
	private Integer messageType;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 是否推送
	 */
	private Integer sendStatus;
	
	/**
	 * id
	 */
	private Long appLendRequestId;

	public Integer getSellerNumber() {
		return sellerNumber;
	}

	public void setSellerNumber(Integer sellerNumber) {
		this.sellerNumber = sellerNumber;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Long getAppLendRequestId() {
		return appLendRequestId;
	}

	public void setAppLendRequestId(Long appLendRequestId) {
		this.appLendRequestId = appLendRequestId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
}