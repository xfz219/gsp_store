package com.puhui.app.dao;

import com.puhui.app.po.AppLendTemplate;
import org.apache.ibatis.annotations.Param;

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
	AppLendTemplate getAppLendTemplateMethod(String templetType);

	/**
	 * 更新调查问卷总开关
	 * @return
	 */
	void updateTempletContent(@Param("templetType") String templetType, @Param("templetContent")String templetContent);

}
