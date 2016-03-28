package com.puhui.app.po;

import java.io.Serializable;
import java.sql.Date;

/**
 * @comment 销售人员手机设备变更记录
 * @author liwang
 * @time 2015年8月3日 上午10:42:19
 */
public class AppUserTokenChangeRecord implements Serializable{
	
	private static final long serialVersionUID = -1798436494029332756L;
	private Long id;//主键
	private String salesNo;//销售员工编号
	private String pid;//设备pid
	private String clientTypeName;//设备类型
	private Date updateTime;//修改日期
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getClientTypeName() {
		return clientTypeName;
	}
	public void setClientTypeName(String clientTypeName) {
		this.clientTypeName = clientTypeName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
