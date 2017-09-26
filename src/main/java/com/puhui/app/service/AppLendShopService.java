package com.puhui.app.service;


import com.puhui.app.po.AppLendShop;
import com.puhui.app.po.AppPrizesSecret;

import java.util.List;
import java.util.Map;

public interface AppLendShopService {

	List<AppLendShop> findList(AppLendShop als);

	Map<String,String> addList(Map<String, String> map, List<Map<String, String>> listMap) throws Exception;

}
