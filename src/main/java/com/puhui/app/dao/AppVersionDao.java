package com.puhui.app.dao;


import java.util.List;
import java.util.Map;

/**
 * app升级Dao
 */
public interface AppVersionDao extends BaseDao {

	List<Map<String, Object>> findList();

	/**
	 * 查询user
     */
	List<Map<String, Object>> findUserList();

	/**
	 * 查询customer
	 */
	List<Map<String, Object>> findCustomerList();

	/**
	 * 修改user
	 */
	int updateUser(Map<String,Object> params);

	/**
	 * 修改customer
	 */
	int updateCustomer(Map<String,Object> params);

}
