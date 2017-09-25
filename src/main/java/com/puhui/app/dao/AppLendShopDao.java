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
	 * 查询奖品秘密列表
	 * @param shopName
	 * @param enabled
	 * @return
	 */
	List<AppLendShop> findList(@Param("shopName") String shopName, @Param("enabled") Boolean enabled);


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
