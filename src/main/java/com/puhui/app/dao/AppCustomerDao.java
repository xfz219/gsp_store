package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

public interface AppCustomerDao extends BaseDao {

	/**
	 * @comment 查询手机号
	 * @author lichunyue
	 * @return
	 */
	public List<Map<String, Object>> getMobileMethod(String mobile);

}
