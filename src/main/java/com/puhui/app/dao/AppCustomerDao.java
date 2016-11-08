package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.AppCustomer;

public interface AppCustomerDao extends BaseDao {

	/**
	 * @comment 查询手机号
	 * @author lichunyue
	 * @return
	 */
	public List<Map<String, Object>> getMobileMethod(String mobile);
    /**
     * 获取所有信息
     * 
     * @author lichunyue
     * @param AppCustomer
     * @return
     */
    public AppCustomer query(long id);

}
