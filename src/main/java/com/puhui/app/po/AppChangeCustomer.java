package com.puhui.app.po;

import java.util.Date;

public class AppChangeCustomer {
	
		private String name;
	    
	    private String mobile;
	  
	    private String status;
	    
	    private String shopName;
	    
	    private String salesName;
	    
	    private String salesNo;

	    private String salesStatus;
	    
	    private String salesMobile;
	    
	    private Date createTime;

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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
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

		public String getSalesStatus() {
			return salesStatus;
		}

		public void setSalesStatus(String salesStatus) {
			this.salesStatus = salesStatus;
		}

		public String getSalesMobile() {
			return salesMobile;
		}

		public void setSalesMobile(String salesMobile) {
			this.salesMobile = salesMobile;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		@Override
		public String toString() {
			return "AppChangeCustomer [name=" + name + ", mobile=" + mobile
					+ ", status=" + status + ", shopName=" + shopName
					+ ", salesName=" + salesName + ", salesNo=" + salesNo
					+ ", salesStatus=" + salesStatus + ", salesMobile="
					+ salesMobile + ", createTime=" + createTime + "]";
		}
	    
}
