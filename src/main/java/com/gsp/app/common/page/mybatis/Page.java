package com.gsp.app.common.page.mybatis;

import java.io.Serializable;

/**
 * @comment 分页类
 * @author liwang
 * @time 2015年7月21日 下午1:24:42
 */
public class Page implements Serializable{
	
	private static final long serialVersionUID = -5211929776746304450L;
	
	//-- 分页参数 --//
	protected int pageNo = 1; //当前页号
	protected int pageSize = 10; //页面记录数
	protected Long totalCount = null; //总记录数
	protected int totalPage = 0; //总页数
	
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}
	
	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}
	
	
	/**
	 * 获得每页的记录数量,默认为20.
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 取得总记录数, 默认值为0
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
		if(totalCount < 0){
			this.totalPage = 0;
		}else{
			long count=(totalCount - 1) / pageSize + 1;
			this.totalPage=new Integer(new Long(count).toString()).intValue();
		}
		if(this.pageNo > this.totalPage){
			this.pageNo=this.totalPage;
		}
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public int getTotalPage() {
		return this.totalPage;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPage());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * Page对象实例化工厂方法
	 * @param pageNo
	 * @return
	 */
	public static Page getPage(Integer pageNo,Integer pageSize){
		Page page = new Page();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}
	
}
