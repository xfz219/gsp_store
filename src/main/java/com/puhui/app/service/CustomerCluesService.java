package com.puhui.app.service;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.AppUserToPromote;


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


}
