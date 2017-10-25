package com.puhui.app.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface AppChangeCustomerDao extends BaseDao<Object>{

	public List<Map<String,Object>> selectChangeCustomerMethod(Map<String,Object> map);
	
	public void updateBindingUserMethod(Map<String,Object> map);
	
	public List<Map<String,Object>> selectAppCustomerMethod(Map<String,Object> map);

	List<Map<String,Object>> findLogInfo(Map<String, Object> map);

	void updateCustomerName(@Param("mobile") String mobile, @Param("name") String name);
}
