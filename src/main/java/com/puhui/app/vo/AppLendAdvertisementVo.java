package com.puhui.app.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告位实体类VO
 * 
 */
public class AppLendAdvertisementVo implements Serializable {
	private static final long serialVersionUID = 6797089384803863317L;
	
	private Long id;
	private String name;//广告名称
	private String customerIdentity;//客户身份，0:薪类,1:商类
    private String url;//广告跳转链接
    private int picSize;//文件大小
    private String originalPicName;//文件原名
    private String picAccessUrl;//图片访问url
    private String picAddressUrl;//图片地址url
    private int sort;//该客户身份和进件状态的广告位的排位
    private Integer enabled;//是否启用，0:否，1:是
    private Date createTime;//创建时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerIdentity() {
		return customerIdentity;
	}
	public void setCustomerIdentity(String customerIdentity) {
		this.customerIdentity = customerIdentity;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPicSize() {
		return picSize;
	}
	public void setPicSize(int picSize) {
		this.picSize = picSize;
	}
	public String getOriginalPicName() {
		return originalPicName;
	}
	public void setOriginalPicName(String originalPicName) {
		this.originalPicName = originalPicName;
	}
	public String getPicAccessUrl() {
		return picAccessUrl;
	}
	public void setPicAccessUrl(String picAccessUrl) {
		this.picAccessUrl = picAccessUrl;
	}
	public String getPicAddressUrl() {
		return picAddressUrl;
	}
	public void setPicAddressUrl(String picAddressUrl) {
		this.picAddressUrl = picAddressUrl;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
    
