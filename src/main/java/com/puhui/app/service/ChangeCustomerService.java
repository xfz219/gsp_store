package com.puhui.app.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.puhui.app.vo.QueryChangeCustomerVo;

/**
 * 
 * 更换销售绑定
 * @author yangzq
 *
 */
public interface ChangeCustomerService {

	public Map<String, Object> selectChangeCustomerMethod(QueryChangeCustomerVo queryChangeCustomerVo);
	
	public List<Map<String, Object>> selectAppCustomerMethod(List<Long> ids);
	
	public boolean validate(List<Long> ids);
	
	public JSONArray  selectUserNameMethod(Long oid);
	
	public void updateBindingUserMethod(List<Long> ids,String selectUserName);
	
}
