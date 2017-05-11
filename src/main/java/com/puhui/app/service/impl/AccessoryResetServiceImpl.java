package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.common.constant.Select;
import com.puhui.app.dao.AppLendAnnexAtatusDao;
import com.puhui.app.service.AccessoryResetService;

@Service
public class AccessoryResetServiceImpl implements AccessoryResetService{
	
	@Autowired
	private AppLendAnnexAtatusDao appLendAnnexAtatusDao;
	/**
	 * @comment 附件查询（进件号、附件类型）
	 * @author lichunyue
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getAccessoryResetServer(Map<String, Object> paramMap)throws Exception{
		List<Map<String, Object>> list = appLendAnnexAtatusDao.getAccessoryResetServer(paramMap);
		List<Map<String, Object>> arrayList = new ArrayList<>();
		for(Map<String, Object> map : list){
			Map<String, Object> map1 = new HashMap<>();
			map1.put("app_lend_request_id", map.get("app_lend_request_id"));
			map1.put("type", Select.getInstance().checkTypeMap().get(map.get("type")));
			arrayList.add(map1);
		}
		return arrayList;
		 
	}
	/**
	 * @comment 重置获取状态与轮询次数
	 * @author lichunyue
	 */
	@Override
	public void updateAccessoryServer(Map<String, Object> paramMap)throws Exception {
		appLendAnnexAtatusDao.updateAccessoryServer(paramMap);
	}
}
