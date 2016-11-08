package com.puhui.app.dao;

import com.puhui.app.po.AppLendTemplate;
/**
 * 
 * @author lcy
 *
 */
public interface AppLendTemplateDao extends BaseDao {

	/**
	 * @comment 根据模板类型查询模板
	 * @param templetType
	 * @return
	 */
	public AppLendTemplate getAppLendTemplateMethod(String templetType);

}
