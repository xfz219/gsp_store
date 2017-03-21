package com.puhui.app.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.dao.AppLendRequestDao;
import com.puhui.app.manager.UpdateCustomerEntryStateManager;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.service.UpdateCustomerEntryStateService;
import com.puhui.app.utils.CitySet;
import com.puhui.uc.vo.RemoteOrganizationVo;



@Service
public class UpdateCustomerEntryStateServiceImpl implements UpdateCustomerEntryStateService{
	
	@Autowired
	private UpdateCustomerEntryStateManager updateCustomerEntryStateManager;
	@Autowired
	private AppLendRequestDao appLendRequestDao;
	@Autowired
	private SwaggerService swaggerService;
	
	/**
	 * 质回未上传
	 * @author lichunyue
	 * @return
	 */
	@Override
	public void updateCustomerEntryState(Map<String, Object> map){
		updateCustomerEntryStateManager.updateCustomerEntryState(map);
	}

	@Override
	public void showChangeMobilePushDialog(long id) {
		updateCustomerEntryStateManager.showChangeMobilePushDialog(id);
	}

	@Override
	public void showChangeMobileDelDialog(String mobile) {
		updateCustomerEntryStateManager.showChangeMobileDelDialog(mobile);
	}

	@Override
	public void showChangeIdDelDialog(long id) {
		updateCustomerEntryStateManager.showChangeIdDelDialog(id);
	}

	@Override
	public void updateAppUserToPromote() {
		List<Map<String,Object>> list1 = appLendRequestDao.getAppUserToPromote();
		for(Map<String,Object> map : list1){
			try{
			List<RemoteOrganizationVo> list = swaggerService.like("rpa");
			List<Map<String, Object>> listMapCity = CitySet.getCityMap(list);
			for (Map<String, Object> listMapCityMap : listMapCity) {
				if(map.get("city").equals(listMapCityMap.get("cityName"))){
					List<RemoteOrganizationVo> listShop = swaggerService.orgIdSub(Long.parseLong(String.valueOf(listMapCityMap.get("id"))));
					Random r = new Random();  
					RemoteOrganizationVo lsv = listShop.get(r.nextInt(listShop.size()));
					String name = lsv.getName();
					String code = lsv.getCode();
					String id = map.get("id").toString();
					appLendRequestDao.updateAppUserToPromote(id,name,code);
				}
			}
			}catch(Exception e){
				
			}
		}
			}
		
	}
