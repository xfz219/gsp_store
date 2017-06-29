package com.puhui.app.dao;


import java.util.List;
import java.util.Map;

/**
 * app升级Dao
 */
public interface AppVersionDao extends BaseDao {

	List<Map<String, Object>> findList();

	/**
	 * 修改user
	 */
	int updateUser(Map<String,Object> params);

	/**
	 * 修改customer
	 */
	int updateCustomer(Map<String,Object> params);

}
