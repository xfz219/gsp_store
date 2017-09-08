package com.puhui.app.service;


import com.puhui.app.po.AppPrizesSecret;

import java.util.List;
import java.util.Map;

public interface PrizesService {

	List<AppPrizesSecret> findList(AppPrizesSecret appPrizesSecret);

	Map<String,String> addList(Map<String,String> map, List<Map<String, String>> listMap) throws Exception;

}
