package com.puhui.app.vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * @comment 销售人员手机设备变更记录
 * @author liwang
 * @time 2015年8月3日 上午10:42:19
 */
public class AppUserTokenChangeRecordVo implements Serializable{
	
	private static final long serialVersionUID = -1798436494029332756L;
	private Long id;//主键
	private Long sellerNumber;//销售员工号
	private String salesNo;//销售员工编号
	private Long appKeyId;//keyid
	private String pid;//设备pid
	private Integer clientType;//设备类型
	private Integer loginStatus;//登录状态
	private String token;//token
	private String version;//版本
	private Date createTime;//创建日期
	private Date updateTime;//修改日期
	private String alias;//别名
	private Integer isEnable;//是否激活状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSellerNumber() {
		return sellerNumber;
	}
	public void setSellerNumber(Long sellerNumber) {
		this.sellerNumber = sellerNumber;
	}
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
	public Long getAppKeyId() {
		return appKeyId;
	}
	public void setAppKeyId(Long appKeyId) {
		this.appKeyId = appKeyId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
}
