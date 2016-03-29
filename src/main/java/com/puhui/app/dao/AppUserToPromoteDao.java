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
	public List<Map<String, Object>> selectCustomerCluesMethod(Map<String, Object> paramMap);

}
