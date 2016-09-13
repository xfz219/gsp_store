package com.puhui.app.vo;

public class Pages {
	/**
	 * 页码
	 */
	private Integer pageNo;
	
	/**
	 * 每页条数
	 */
	private Integer pageSize;
	
	/**
	 * 分页sql
	 */
	private String paging;
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPaging() {
		if(null!=this.pageNo && this.pageSize!=null){
			paging = " limit "+(this.pageNo*this.pageSize)+","+this.pageSize;
		}
		return paging;
	}

	public void setPaging(String paging) {
		this.paging = paging;
	}
	
}
