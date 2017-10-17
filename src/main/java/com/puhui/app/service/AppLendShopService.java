package com.puhui.app.service;


import com.puhui.app.po.AppLendShop;
import com.puhui.app.po.AppPrizesSecret;

import java.util.List;
import java.util.Map;

public interface AppLendShopService {

	List<AppLendShop> findList(AppLendShop als);

	void updateAppLendShop(AppLendShop als);

	void addAppLendShop(AppLendShop als);

	void updateEnabledById(long l, boolean b);
}
