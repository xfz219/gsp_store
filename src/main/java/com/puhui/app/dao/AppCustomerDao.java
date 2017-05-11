package com.puhui.app.dao;

import com.puhui.app.po.AppCustomer;

import java.util.List;
import java.util.Map;

public interface AppCustomerDao extends BaseDao {

	/**
	 * 查询身份证号
     */
	List<Map<String, Object>> getIdNoMethod(String idNo);

	/**
	 * 查询手机号
	 */
	List<Map<String, Object>> getMobileMethod(String mobile);

    /**
     * 获取所有信息
     */
    AppCustomer queryById(Long id);

}
