package com.puhui.app.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.puhui.app.po.AppUserToPromote;

import net.sf.json.JSONArray;


public interface CustomerCluesService {
	/**
	 * @comment 线索查询
	 * @author lichunyue
	 * @return
	 */
	public List<Map<String, Object>> selectCustomerCluesMethod(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * @comment 绑定
	 * @author lichunyue
	 * @return
	 */
	public void updateBindingUserMethod(Integer toPromoteId, String selectUserName)throws Exception;
	/**
	 * @comment 销售查询
	 * @author lichunyue
	 * @return
	 */
	public JSONArray selectUserNameMethod(String department,String shopCode);
	
	public String getUserInfoMethod(String salesNo,String city) throws Exception;

	public AppUserToPromote findCustomerCluesMethod(Long id);
	
	public void insertAppUserToPromote(JSONObject jSONObject);
	//获取渠道
	public List<Map<String,Object>> findChannl();
	//根据身份证号查询是否存在进件
	public int getUserInfoMethodIdNo(String idNo);
	//根据手机号查询是否存在进件
	public int getUserInfoMobile(String mobile);
}
