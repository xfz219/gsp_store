package com.puhui.app.service;

import java.util.List;
import java.util.Map;


public interface CustomerCluesService {
	/**
	 * @comment 线索查询
	 * @author lichunyue
	 * @return
	 */
	public List<Map<String, Object>> selectCustomerCluesMethod(Map<String, Object> paramMap)throws Exception;

}
