package com.puhui.app.service;

import com.puhui.app.po.AppCustomer;
import com.puhui.app.vo.QueryChangeCustomerVo;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * 
 * 更换销售绑定
 * @author yangzq
 *
 */
public interface ChangeCustomerService {

	Map<String, Object> selectChangeCustomerMethod(QueryChangeCustomerVo queryChangeCustomerVo);
	
	List<Map<String, Object>> selectAppCustomerMethod(List<Long> ids);
	
	boolean validate(List<Long> ids);
	
	JSONArray  selectUserNameMethod(Long oid);
	
	void updateBindingUserMethod(List<Long> ids,String selectUserName);
	
	void insertLog(String ids, Long staffId);

	AppCustomer selectCustomerById(Long customerId);

}
