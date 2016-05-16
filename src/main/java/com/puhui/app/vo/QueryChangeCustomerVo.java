package com.puhui.app.vo;

import java.io.Serializable;

public class QueryChangeCustomerVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int page = 0;
	
	private int rows = 20;
	
	private String staffCode;// 机构
	
	private Long staffId;
	
	private String position;

	private String sort = "lendRequestId";// 排序字段

	private String order = "desc";// 排序方式
	
	private String name; //客户姓名
	
	private String mobile; //客户手机号
	
	private Long pid;
	
	private String salesStatus; //销售状态
	
	private String salesName; //销售姓名
	
	private String salesNo; //销售工号
	
	private String salesMobile; //销售手机号
	
	private String department; //机构

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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

	public String getSalesStatus() {
		return salesStatus;
	}

	public void setSalesStatus(String salesStatus) {
		this.salesStatus = salesStatus;
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

	public String getSalesMobile() {
		return salesMobile;
	}

	public void setSalesMobile(String salesMobile) {
		this.salesMobile = salesMobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "QueryChangeCustomerVo [page=" + page + ", rows=" + rows
				+ ", staffCode=" + staffCode + ", staffId=" + staffId
				+ ", position=" + position + ", sort=" + sort + ", order="
				+ order + ", name=" + name + ", mobile=" + mobile
				+ ", salesStatus=" + salesStatus + ", salesName=" + salesName
				+ ", salesNo=" + salesNo + ", salesMobile=" + salesMobile
				+ ", department=" + department + "]";
	}
	
	
}
