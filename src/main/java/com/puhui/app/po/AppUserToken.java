package com.puhui.app.po;

import java.util.Date;

import com.puhui.app.po.BaseBean;

/**
 * 销售token
 * @author lichunyue
 *
 */
public class AppUserToken extends BaseBean{
    private static final long serialVersionUID = 1697870832155269358L;
    /**
     * 销售员工id
     */
    private Long sellerNumber;
    
    /**
     * 销售员工号
     */
    private String salesNo;
    

	/**
     * 公钥、私钥id
     */
    private Long appKeyId;
    /**
     * 手机唯一标识
     */
    private String pid;
    /**
     * 客户端类型：1网站;2苹果;3安卓;4其他
     */
    private Integer clientType;
    /**
     * 登录状态：1未登录；2已登录
     */
    private Integer loginStatus;
    /**
     * token
     */
    private String token;
    /**
     * 版本号
     */
    private String version;
    
    /**
     * 是否启用
     */
    private String isEnable;
    
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
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

	public String getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
    
    
    
}
