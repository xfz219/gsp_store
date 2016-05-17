package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.dao.AppUserToPromoteDao;
import com.puhui.app.po.AppUserToPromote;
import com.puhui.app.service.CustomerCluesService;
import com.puhui.uc.api.service.RemoteLendAppUserCenterService;
import com.puhui.uc.api.service.RemoteStaffService;
import com.puhui.uc.vo.RemoteLendAppResultVo;
import com.puhui.uc.vo.RemoteOrganizationVo;
import com.puhui.uc.vo.RemoteStaffVo;

@Service
public class CustomerCluesServiceImpl implements CustomerCluesService{
	
	@Autowired
	private AppUserToPromoteDao appUserToPromoteDao;
	@Autowired
	private AppCustomerDao appCustomerDao;
	@Autowired
	private RemoteLendAppUserCenterService remoteLendAppUserCenterService;
	@Autowired
	private RemoteStaffService remoteStaffService;
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
    		autpMap.put("branchCode", autp.getBranchCode());
    		autpMap.put("salesName", autp.getSalesName());
    		autpMap.put("salesNo", autp.getSalesNo());
    		autpMap.put("department", autp.getDepartment());
    		Map<String, Object> map = appCustomerDao.getMobileMethod(autp.getMobile());
    		autpMap.put("registered", map == null ? "未注册" : "已注册");
    		autpMap.put("sales", autp.getSalesNo() == null ? "否" : "是");
    		autpMap.put("salesStatus", autp.getSalesNo() == null ? "" : getUserInfoMethod(autp.getSalesNo(),autp.getCityCode()));
    		if(salesStatus != null && salesStatus != ""){
    			if(!salesStatus.equals(autpMap.get("salesStatus"))){
    				continue;
    			}
    		}
    		list.add(autpMap);
    	}
		return list;
	}
	
	@Override
	public void updateBindingUserMethod(Integer toPromoteId,String selectUserName) throws Exception {
		RemoteLendAppResultVo remoteLendAppResultVo = remoteLendAppUserCenterService.getUserInfoMethod(selectUserName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", toPromoteId);
		map.put("name", remoteLendAppResultVo.getName());
		map.put("salesNo", selectUserName);
		map.put("department", remoteLendAppResultVo.getDepartment());
		appUserToPromoteDao.updateBindingUserMethod(map);
	}

	/**
	 * @comment 查询是否异常
	 * @author lichunyue
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String getUserInfoMethod(String salesNo,String cityCode) throws Exception{
		boolean salesStatus = false;//false异常/true正常
		RemoteLendAppResultVo remoteLendAppResultVo = remoteLendAppUserCenterService.getUserInfoMethod(salesNo);
		  if (remoteLendAppResultVo.getCode() == 1) {
			if (remoteLendAppResultVo.isEnabled() == true
					&& remoteLendAppResultVo.getPosition().equals("个贷-销售")
					&& getUserCityNameMethod(cityCode.trim(),remoteLendAppResultVo.getShopCode().trim())) {
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
	public boolean getUserCityNameMethod(String cityCode,String shopCode) throws Exception {
		boolean cityName = true;
		char[] cityC = cityCode.toCharArray();
		char[] shopNameC = shopCode.toCharArray();
		for(int i = 0;i < cityC.length;i++){
			if(cityC[i] != shopNameC[i]){
				cityName = false;
				break;
			}
		}
		return cityName;
	}
	/**
	 * @comment 查询销售
	 * @author lichunyue
	 * @return
	 */
	@Override
	public JSONArray selectUserNameMethod(String department,String shopCode) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		RemoteStaffVo remoteStaffVo = new RemoteStaffVo();
		RemoteOrganizationVo organizationVo = new RemoteOrganizationVo();
		organizationVo.setCode(shopCode+"%");
//		organizationVo.setName(department);
		remoteStaffVo.setOrganizationVo(organizationVo);
		remoteStaffVo.setEnabled(true);//在职
		remoteStaffVo.setPositionType("SALES");//销售
		List<RemoteStaffVo> remoteStaffVoList =  remoteStaffService.query(0, 0, remoteStaffVo);
		for(RemoteStaffVo remoteStaffVoName : remoteStaffVoList){
			if(remoteStaffVoName.getOrganizationVo().getName().equals(department)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("selectUserCode", remoteStaffVoName.getEmployeeNo());
				map.put("selectUserName", remoteStaffVoName.getRealName());
				listMap.add(map);
			}
		}
		return JSONArray.fromObject(listMap);
	}
}
