package com.puhui.app.service;

import java.util.List;
import java.util.Map;


public interface AccessoryResetService {
	/**
	 * @comment 附件查询（进件号、附件类型）
	 * @author lichunyue
	 * @return
	 */
	public List<Map<String, Object>> getAccessoryResetServer(Map<String, Object> paramMap)throws Exception;
	/**
	 * @comment 重置获取状态与轮询次数
	 * @author lichunyue
	 */
	public void updateAccessoryServer(Map<String, Object> paramMap)throws Exception;
}
