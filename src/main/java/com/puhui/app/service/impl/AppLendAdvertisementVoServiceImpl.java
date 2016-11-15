package com.puhui.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.dao.AppLendAdvertisementDao;
import com.puhui.app.service.AppLendAdvertisementVoService;
import com.puhui.app.vo.AppLendAdvertisementVo;
/**
 * 
 * @author lcy
 *
 */
@Service
public class AppLendAdvertisementVoServiceImpl implements AppLendAdvertisementVoService{
	
	@Autowired
	private AppLendAdvertisementDao appLendAdvertisementDao;
	@Override
	public Map<String, Object> queryLendAdvertisementList(AppLendAdvertisementVo appLendAdvertisementVo) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = appLendAdvertisementDao.queryLendAdvertisementList(appLendAdvertisementVo);
        map.put("rows", list);
		return map;
	}
	
	@Override
	public AppLendAdvertisementVo selectLendAdvertisementById(Long id) {
		return appLendAdvertisementDao.selectLendAdvertisementById(id);
	}

	@Override
	public AppLendAdvertisementVo getLendAdvertisementByIdentityAndStatus(AppLendAdvertisementVo lendAd) {
		
		return null;
	}
	
}
