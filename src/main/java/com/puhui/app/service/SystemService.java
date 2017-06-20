package com.puhui.app.service;


import com.puhui.app.vo.AppPushMessageVo;

import java.util.List;
import java.util.Map;

public interface SystemService {

	List<Map<String, Object>> findList();

	boolean update(Map<String, Object> params);

	List<Map<String, Object>> findUserList();

	List<Map<String, Object>> findCustomerList();

	/**
	 * 修改user
	 */
	void updateUser(Map<String,Object> params);

	/**
	 * 修改customer
	 */
	void updateCustomer(Map<String,Object> params);

}
