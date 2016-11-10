package com.puhui.app.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告位实体类VO
 * 
 */
public class AppLendAdvertisementVo implements Serializable {
	private static final long serialVersionUID = 6797089384803863317L;
	
	private String name;       //广告名称
	private String customerIdentity;//客户身份，0:薪类,1:商类
    private String url;        //广告跳转链接
    private String enabled;        //是否启用，0:否，1:是
    private Date createTime;   //创建时间

    private int page;
    private int rows;
    
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCustomerIdentity() {
		return customerIdentity;
	}
	public void setCustomerIdentity(String customerIdentity) {
		this.customerIdentity = customerIdentity;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
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
    
}
