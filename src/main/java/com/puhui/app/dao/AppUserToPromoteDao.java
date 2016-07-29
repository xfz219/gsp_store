package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.AppUserToPromote;

public interface AppUserToPromoteDao extends BaseDao<AppUserToPromote> {

	/**
	 * @comment 线索查询
	 * @author lichunyue
	 * @return
	 */
	public List<AppUserToPromote> selectCustomerCluesMethod(Map<String, Object> paramMap);
	
	public void updateBindingUserMethod(Map<String, Object> map);
	
	public AppUserToPromote findCustomerCluesMethod(Long id);
	
	public void insertAppUserToPromote(AppUserToPromote appUserToPromote);
	
	public List<Map<String,Object>> findChannels();
	
	public Map<String,Object> findChannel(String name);

}
