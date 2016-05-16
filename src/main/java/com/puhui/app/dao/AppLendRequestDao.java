package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

public interface AppLendRequestDao extends BaseDao{

	void updateCustomerEntryState(long id);
	
	List<String> selectAppLendRequestMethod(Map<String,Object> map);
	
	void updateCustomerSalesNo(Map<String,Object> map);
}
