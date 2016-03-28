package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

public interface AppDataCachingOperationsDao extends BaseDao{
	/**
	 * @comment 附件查询（进件号、附件类型）
	 * @author lichunyue
	 * @return
	 */
	public List<Map<String, Object>> getDataServer(Map<String, Object> paramMap);
	/**
	 * @comment 缓存表数据修改
	 * @author lichunyue
	 */
	public void updateDataServer(Map<String, Object> updateMap);
	/**
	 * @comment 缓存表数据新增
	 * @author lichunyue
	 */
	public void addDataServer(Map<String, Object> addMap);

}
