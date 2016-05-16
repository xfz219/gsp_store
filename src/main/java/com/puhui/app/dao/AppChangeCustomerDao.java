package com.puhui.app.dao;

import java.util.List;
import java.util.Map;


public interface AppChangeCustomerDao extends BaseDao<Object>{

	public List<Map<String,Object>> selectChangeCustomerMethod(Map<String,Object> map);
	
	public void updateBindingUserMethod(Map<String,Object> map);
	
	public List<Map<String,Object>> selectAppCustomerMethod(Map<String,Object> map);
}
