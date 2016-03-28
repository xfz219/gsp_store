package com.puhui.app.service;

import java.util.List;
import java.util.Map;


public interface DataCachingOperationsService {
	/**
	 * @comment 查询缓存表数据
	 * @author lichunyue
	 * @return
	 */
	public List<Map<String, Object>> getDataServer(Map<String, Object> paramMap)throws Exception;
	/**
	 * @comment 缓存表数据修改
	 * @author lichunyue
	 */
	public void updateDataServer(Map<String, Object> updateMap)throws Exception;
	/**
	 * @comment 缓存表数据新增
	 * @author lichunyue
	 */
	public void addDataServer(Map<String, Object> addMap)throws Exception;
}
