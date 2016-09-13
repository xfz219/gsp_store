package com.puhui.app.po;

import java.util.Date;

import com.puhui.app.vo.Pages;

/**
 * 客户token
 * @author xiaobowen
 *
 */
public class AppCustomerToken extends Pages{
    private static final long serialVersionUID = -6304432282460137514L;
    
    /**
     * 销售员工编号
     * @author xiaobowen
     */
    private Long sellerNumber;
    
    /**
     * 用户id
     * @author xiaobowen
     */
    private Long appCustomerId;
    /**
     * 公钥、私钥id
     * @author xiaobowen
     */
    private Long appKeyId;
    /**
     * 手机唯一标识
     * @author xiaobowen
     */
    private String pid;
    /**
     * 客户端类型：1网站;2苹果;3安卓;4其他
     * @author xiaobowen
     */
    private Integer clientType;
    /**
     * 登录状态：1未登录；2已登录
     * @author xiaobowen
     */
    private Integer loginStatus;
    /**
     * token
     * @author xiaobowen
     */
    private String token;
    /**
     * 版本号
     * @author xiaobowen
     */
    private String version;
    
    /**
     * 是否启用
     * @author xiaobowen
     */
    private String isEnable;
    
	/**
     * 创建时间
     * @author xiaobowen
     */
    private Date createTime;
    /**
     * 更新时间
     * @author xiaobowen
     */
    private Date updateTime;
    /**
     * alias别名
     * @return
     */
    private String alias;
	public Long getSellerNumber() {
		return sellerNumber;
	}
	public void setSellerNumber(Long sellerNumber) {
		this.sellerNumber = sellerNumber;
	}
	public Long getAppCustomerId() {
		return appCustomerId;
	}
	public void setAppCustomerId(Long appCustomerId) {
		this.appCustomerId = appCustomerId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
    
	
}
