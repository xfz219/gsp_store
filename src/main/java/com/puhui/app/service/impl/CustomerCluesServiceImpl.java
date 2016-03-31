package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.dao.AppUserToPromoteDao;
import com.puhui.app.po.AppUserToPromote;
import com.puhui.app.service.CustomerCluesService;
import com.puhui.uc.api.service.RemoteLendAppUserCenterService;
import com.puhui.uc.api.service.RemoteOrganizationService;
import com.puhui.uc.vo.RemoteLendAppResultVo;
import com.puhui.uc.vo.RemoteOrganizationVo;

@Service
public class CustomerCluesServiceImpl implements CustomerCluesService{
	
	@Autowired
	private AppUserToPromoteDao appUserToPromoteDao;
	@Autowired
	private AppCustomerDao appCustomerDao;
	@Autowired
	private RemoteLendAppUserCenterService remoteLendAppUserCenterService;
	@Autowired
	private RemoteOrganizationService remoteOrganizationService;
	/**
	 * @comment 线索查询
	 * @author lichunyue
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectCustomerCluesMethod(Map<String, Object> paramMap) throws Exception {
		List<AppUserToPromote> autpList = appUserToPromoteDao.selectCustomerCluesMethod(paramMap);
		Object salesStatus = paramMap.get("salesStatus");
		List<Map<String, Object>> list =  new ArrayList<Map<String, Object>>();
		for(AppUserToPromote autp : autpList){
    		Map<String, Object> autpMap = new HashMap<String, Object>();
    		autpMap.put("toPromoteId", autp.getId());
    		autpMap.put("name", autp.getName());
    		autpMap.put("mobile", autp.getMobile());
    		autpMap.put("city", autp.getCity());
    		autpMap.put("branch", autp.getBranch());
    		autpMap.put("salesName", autp.getSalesName());
    		autpMap.put("salesNo", autp.getSalesNo());
    		Map<String, Object> map= appCustomerDao.getMobileMethod(autp.getMobile());
    		autpMap.put("registered", map == null ? "未注册" : "已注册");
    		autpMap.put("sales", autp.getSalesNo() == null ? "否" : "是");
    		autpMap.put("salesStatus", autp.getSalesNo() == null ? "" : getUserInfoMethod(autp.getSalesNo(),autp.getCity()));
    		if(salesStatus != null && salesStatus != ""){
    			if(!salesStatus.equals(autpMap.get("salesStatus"))){
    				continue;
    			}
    		}
    		list.add(autpMap);
    	}
		return list;
	}
	/**
	 * @comment 查询是否异常
	 * @author lichunyue
	 * @return
	 */
	public String getUserInfoMethod(String salesNo,String city) throws Exception {
		boolean salesStatus = false;//false异常/true正常
		RemoteLendAppResultVo remoteLendAppResultVo = remoteLendAppUserCenterService.getUserInfoMethod(salesNo);
		  if (remoteLendAppResultVo.getCode() == 1) {
			if (remoteLendAppResultVo.isEnabled() == true
					&& remoteLendAppResultVo.getPosition().equals("个贷-销售")
					&& getUserCityNameMethod(city.trim(),remoteLendAppResultVo.getShopName().trim())) {
				salesStatus = true;
              }
		  }
		return salesStatus == false ? "异常" : "正常";
	}
	/**
	 * @comment 查询城市
	 * @author lichunyue
	 * @return
	 */
	public boolean getUserCityNameMethod(String city,String shopName) throws Exception {
		boolean cityName = true;
		char[] cityC = city.toCharArray();
		char[] shopNameC = shopName.toCharArray();
		for(int i = 0;i < cityC.length;i++){
			if(cityC[i] != shopNameC[i]){
				cityName = false;
				break;
			}
		}
		return cityName;
	}

}
