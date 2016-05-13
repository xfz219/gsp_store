package com.puhui.app.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @comment 接口日志实体类
 * @author liwang
 * @time 2015年7月10日 下午3:59:01
 */
public class AppInterfaceLogVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1625759454464067724L;
	
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 接口类型
	 */
	private Integer interfaceType;
	/**
	 * 接口名称
	 */
	private String interfaceTypeName;
	/**
	 * 请求参数
	 */
	private String requestParam;
	/**
	 * 是否成功 0：失败  1：成功
	 */
	private Integer isSuccess;
	/**
	 * 反馈信息
	 */
	private String message;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getInterfaceType() {
		return interfaceType;
	}
	public void setInterfaceType(Integer interfaceType) {
		this.interfaceType = interfaceType;
	}
	public String getInterfaceTypeName() {
		return interfaceTypeName;
	}
	public void setInterfaceTypeName(String interfaceTypeName) {
		this.interfaceTypeName = interfaceTypeName;
	}
	public String getRequestParam() {
		return requestParam;
	}
	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}
	public Integer getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
	
}
