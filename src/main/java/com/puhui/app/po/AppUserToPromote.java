package com.puhui.app.po;

import java.io.Serializable;
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
	
}
