package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.dao.AppChangeCustomerDao;
import com.puhui.app.dao.AppInterfaceLogDao;
import com.puhui.app.dao.AppLendRequestDao;
import com.puhui.app.service.AppPushService;
import com.puhui.app.service.ChangeCustomerService;
import com.puhui.app.service.CustomerCluesService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.vo.QueryChangeCustomerVo;
import com.puhui.uc.vo.RemoteOrganizationVo;
import com.puhui.uc.vo.RemoteStaffVo;

import net.sf.json.JSONArray;

@Service
public class ChangeCustomerServiceImpl implements ChangeCustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ChangeCustomerServiceImpl.class);

	@Autowired
	private AppChangeCustomerDao appChangeCustomerDao;
	
	@Autowired
	private CustomerCluesService customerCluesService;
	
	@Autowired
	private SwaggerService swaggerService;
	
	@Autowired
	private AppPushService appPushService;
	
	@Autowired
	private AppLendRequestDao appLendRequestDao;
	
	@Autowired
	private AppInterfaceLogDao appInterfaceLogDao;
	@Override
	public Map<String, Object> selectChangeCustomerMethod(
			QueryChangeCustomerVo queryChangeCustomerVo) {
		 	Map<String, Object> map = new HashMap<String, Object>();
	        List<Map<String, Object>> list = null;
	       
	        List<Map<String, Object>> objList =  new ArrayList<Map<String, Object>>();
	        List<String> listSales = new ArrayList<String>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	      /*  int size = queryChangeCustomerVo.getRows();// 查询个数
            int page = queryChangeCustomerVo.getPage();// 页数
            if (page == 0) {
                page = 1;
            }
            int total = 0;// 总数
            int from = (queryChangeCustomerVo.getPage() - 1) * queryChangeCustomerVo.getRows();
            queryChangeCustomerVo.setPage(from);*/
            
            Page page = Page.getPage(queryChangeCustomerVo.getPage(),queryChangeCustomerVo.getRows());
            paramMap.put("page", page);
	        paramMap.put("name", queryChangeCustomerVo.getName() != null? queryChangeCustomerVo.getName()+"%": "");
        	paramMap.put("mobile", queryChangeCustomerVo.getMobile() != null? queryChangeCustomerVo.getMobile()+"%": "");
        	paramMap.put("salesmobile", queryChangeCustomerVo.getSalesMobile() != null? queryChangeCustomerVo.getSalesMobile()+"%": "");
        	paramMap.put("salesName", queryChangeCustomerVo.getSalesName() != null? queryChangeCustomerVo.getSalesName()+"%": "");
        	paramMap.put("salesNo", queryChangeCustomerVo.getSalesNo() != null? queryChangeCustomerVo.getSalesNo()+"%": "");
        	paramMap.put("staffCode", !queryChangeCustomerVo.getStaffCode().equals("RPA") ? queryChangeCustomerVo.getStaffCode()+"%": "");	
        	paramMap.put("listSales", listSales);
	        try {
		        
	        	 list = appChangeCustomerDao.selectChangeCustomerMethod(paramMap);
	        	 logger.info("--------------查询销售绑定列表  结束----------------");
	        	 for(Map<String, Object> ml : list){
	        		 
	        		String salesStatus = this.getUserInfoMethod(ml.get("salesNo") == null ? "":ml.get("salesNo").toString(), ml.get("shopName") == null ?"": ml.get("shopName").toString());
	        		ml.put("salesStatus", salesStatus);
	        		objList.add(ml);
	        		
	        	 }
	        } catch (Exception e) {
	            logger.error("query selectChangeCustomerMethod error!" + e);
	        }
	       
	    	map.put("total", page.getTotalCount());
	    	map.put("rows", objList);
    return map;
	}


	@Override
	public JSONArray selectUserNameMethod(Long oid) {
		String shopCode = swaggerService.orgId(oid).getCode();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		RemoteStaffVo remoteStaffVo = new RemoteStaffVo();
		RemoteOrganizationVo organizationVo = new RemoteOrganizationVo();
		organizationVo.setCode(shopCode+"%");
//		organizationVo.setName(department);
		remoteStaffVo.setOrganizationVo(organizationVo);
		remoteStaffVo.setEnabled(true);//在职
		remoteStaffVo.setPositionType("SALES");//销售
		List<RemoteStaffVo> remoteStaffVoList = swaggerService.ucPage(0, 0, remoteStaffVo);
		for(RemoteStaffVo remoteStaffVoName : remoteStaffVoList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("selectUserCode", remoteStaffVoName.getEmployeeNo());
				map.put("selectUserName", remoteStaffVoName.getRealName());
				listMap.add(map);
			
		}
		return JSONArray.fromObject(listMap);
	}


	@Override
	// 指定回滚
	@Transactional(rollbackFor=Exception.class) 
	public void updateBindingUserMethod(List<Long> ids,
			String selectUserName) {
		RemoteStaffVo remoteStaffVo = swaggerService.employeeNo(selectUserName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("name", remoteStaffVo.getRealName());
		map.put("mobile", remoteStaffVo.getMobile());
		map.put("salesNo", remoteStaffVo.getEmployeeNo());
		map.put("salesId", remoteStaffVo.getId());
		map.put("shopCode",remoteStaffVo.getOrganizationVo().getParentVo().getCode());
		map.put("shopName",remoteStaffVo.getOrganizationVo().getParentVo().getName());
		map.put("districtCode",remoteStaffVo.getOrganizationVo().getParentVo().getCode().substring(0, 4));
		
		this.updateAppLendRequest(ids, remoteStaffVo.getId());
		appChangeCustomerDao.updateBindingUserMethod(map);
		this.push(ids, remoteStaffVo.getId());
	}
	
	public boolean validate(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		List<Map<String, Object>> list = appChangeCustomerDao.selectAppCustomerMethod(map);
		String salesStatus = "";
		 for(Map<String, Object> ml : list){
    		 
     		
			try {
				salesStatus = customerCluesService.getUserInfoMethod(ml.get("salesNo").toString(), ml.get("shopName").toString());
			} catch (Exception e) {
				logger.info("查询销售是否异常出错",e);
			}
     		if("正常".equals(salesStatus)){
     			
     			return false;
     		}
     	 }
		 return true;
	}


	@Override
	public List<Map<String, Object>> selectAppCustomerMethod(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		return appChangeCustomerDao.selectAppCustomerMethod(map);
	}
	
	public void push(List<Long> ids,Long salesId){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("number", ids.size());
		map1.put("sellerNumber", salesId);
		map.put("user", map1);
		map.put("customer", ids);
		appPushService.pushUnwrapMessageMethod(map);
	}
	
	public void updateAppLendRequest(List<Long> ids,Long salesId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		List<String> list = appLendRequestDao.selectAppLendRequestMethod(map);
		map.put("ids", list);
		map.put("salesId", salesId);
		if(null != list && list.size() >0){
			appLendRequestDao.updateCustomerSalesNo(map);
		}
	}
	
	public void insertLog(String ids, Long staffId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("interfaceType", 2);
		map.put("interfaceTypeName", "更换销售接口");
		map.put("requestParam", staffId);
		map.put("isSuccess", 1);
		map.put("message", "被更换app_customer_id:----"+ids);
		appInterfaceLogDao.insertLog(map);
	}
	
	public String getUserInfoMethod(String salesNo,String shopName) throws Exception{
		boolean salesStatus = false;//false异常/true正常
		if(!salesNo.equals("")){
			RemoteStaffVo remoteStaffVo = swaggerService.employeeNo(salesNo);
			if (remoteStaffVo.getEnabled() == true
					&& remoteStaffVo.getPositionName().equals("个贷-销售")
					&& remoteStaffVo.getOrganizationVo().getParentVo().getName().equals(shopName)) {
				salesStatus = true;
			}
		}
		return salesStatus == false ? "异常" : "正常";
	}
}
