package com.puhui.app.dao;


import com.puhui.app.po.AppLendShop;
import com.puhui.app.po.AppPrizesSecret;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 门店码表
 */
public interface AppLendShopDao extends BaseDao {


	/**
	 * 获取list
	 * @param shopName
	 * @param enabled
	 * @return
	 */
	List<AppLendShop> findList(@Param("shopName") String shopName, @Param("enabled") Boolean enabled);

	/**
	 * 获取vo
	 * @param id
	 * @return
	 */
	AppLendShop getAppLendShop(@Param("id") Long id);


	/**
	 * 插入
	 * @param appLendShop
	 * @return
	 */
	void addAppLendShop(AppLendShop appLendShop);

	/**
	 * 更新
	 * @param appLendShop
	 * @return
	 */
	void updateAppLendShop(AppLendShop appLendShop);

}
