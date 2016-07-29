package com.puhui.app.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 创新获客
 * 
 * @author LICHUNYUE
 */
public class AppUserToPromote implements Serializable {

    /**
     * @author puhui
     */
    private static final long serialVersionUID = 1L;
    
    private long id;

    private String name;
    
    private String mobile;
    
    private String city;
    
    private String cityCode;
    
    private String branch;
    
    private String branchCode;
    
    private String salesName;
    
    private String salesNo;

    private String uid;
    
    private String department;
    
    private Date updateAllotTime;
    
    private Date createTime;
    
    private String idNo; //身份证

    private BigDecimal amount; // 金额
    
    private String productName; //产品
    
    private Boolean isSettle;    //是否一次性结清
    
    private Date settleTime; //结清日期
    
    private String channel;//渠道值

    private String channelTwo;//渠道标示

    private String province;//省
    
    private String channelType; //一级渠道类型
    
    private String channelTwoType; //二级渠道类型
    
    
	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelTwoType() {
		return channelTwoType;
	}

	public void setChannelTwoType(String channelTwoType) {
		this.channelTwoType = channelTwoType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getChannelTwo() {
		return channelTwo;
	}

	public void setChannelTwo(String channelTwo) {
		this.channelTwo = channelTwo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(Boolean isSettle) {
		this.isSettle = isSettle;
	}

	public Date getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getUpdateAllotTime() {
		return updateAllotTime;
	}

	public void setUpdateAllotTime(Date updateAllotTime) {
		this.updateAllotTime = updateAllotTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
