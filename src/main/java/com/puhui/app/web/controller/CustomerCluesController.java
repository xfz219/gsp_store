package com.puhui.app.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.dao.AppUserToPromoteDao;
import com.puhui.app.po.AppUserToPromote;
import com.puhui.app.service.CustomerCluesService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.utils.JsonTools;
import com.puhui.app.utils.LendAesUtil;
import com.puhui.uc.vo.RemoteStaffVo;

import net.sf.json.JSONArray;

/**
 * @comment 客户线索
 * @author lichunyue
 */
@Controller
@RequestMapping(value = "/customerClues")
public class CustomerCluesController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerCluesController.class);
	@Autowired
	private CustomerCluesService customerCluesService;
	@Autowired
	private AppUserToPromoteDao appUserToPromoteDao;
	@Autowired
	private SwaggerService swaggerService;
	
	
	/**
	 * 页面跳转
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping("/goCustomerCluesMethod")
	public String goAccessoryResetMethod(){
		return "business/customerClues";
	}
	
	/**
	 * @comment 线索查询
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping(value = "/selectCustomerCluesMethod")
	@ResponseBody
	public Map<String, Object> selectCustomerCluesMethod(@RequestParam Map<String, Object> pageMap, 
    		@RequestParam(value = "radio", required = false) String radio,
    		@RequestParam(value = "name", required = false) String name,
    		@RequestParam(value = "mobile", required = false) String mobile,
    		@RequestParam(value = "shopCode", required = false) String shopCode,
    		@RequestParam(value = "salesName", required = false) String salesName,
    		@RequestParam(value = "salesNo", required = false) String salesNo,
    		@RequestParam(value = "channel", required = false) String channel){
		
    	Map<String, Object> objMap = new HashMap<>();
    	try{
    		Subject currStaff = SecurityUtils.getSubject();
    		RemoteStaffVo staff = (RemoteStaffVo) currStaff.getPrincipal();
    		Page page = null;
    		if(pageMap.get("page") != null && pageMap.get("rows") != null){
    			int pageNo = Integer.parseInt(pageMap.get("page").toString());// 当前页
    			int pageSize= Integer.parseInt(pageMap.get("rows").toString());// 当前页大小
    			page = Page.getPage(pageNo,pageSize);
    		}
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<>(); 
        	paramMap.put("page", page);
        	paramMap.put("radio", radio);
        	paramMap.put("name", name != null?name+"%": "");
        	paramMap.put("mobile", mobile != null?LendAesUtil.encrypt(mobile)+"%": "");
        	paramMap.put("salesName", salesName != null?salesName+"%": "");
        	paramMap.put("salesNo", salesNo != null?salesNo+"%": "");
        	paramMap.put("channel", channel != null? channel :"");
        	if(StringUtils.isBlank(shopCode)){
        		paramMap.put("branchCode", staff.getOrganizationVo().getCode()+"%");
        	}else{
        		String branchCode = swaggerService.orgId(Long.parseLong(shopCode)).getCode();
        		paramMap.put("branchCode", branchCode+"%");
        	}
        	List<Map<String, Object>> autpList = customerCluesService.selectCustomerCluesMethod(paramMap);
        	objMap.put("total", page.getTotalCount());
        	objMap.put("rows", autpList);
    	}catch(Exception e){
    		logger.error("线索管理查询失败:",e);
    		throw new IllegalArgumentException(e);
    	}
    	return objMap;
	}
	
	/**
	 * @comment 线索管理绑定
	 * @author lichunyue
	 */
	@RequestMapping(value = "/updateBindingUserMethod")
	@ResponseBody
	public JSONArray updateBindingUserMethod(
			@RequestParam(value = "toPromoteId", required = false) int toPromoteId,
    		@RequestParam(value = "selectUserName", required = false) String selectUserName){
    	try{
    		AppUserToPromote appUserToPromote = appUserToPromoteDao.findCustomerCluesMethod(Long.parseLong(String.valueOf(toPromoteId)));
    		customerCluesService.updateBindingUserMethod(appUserToPromote,selectUserName);
    		return JSONArray.fromObject(1); 
    	}catch(Exception e){
    		logger.error("绑定失败！",e);
    		throw new IllegalArgumentException(e);
    	}
	}
	
	/**
	 * @comment 查询异常
	 * @author lichunyue
	 */
	@RequestMapping(value = "/selectUserSalesStatusMethod")
	@ResponseBody
	public JSONArray selectUserSalesStatusMethod(){
    	try{
    		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("salesStatusCode", "");
    		map.put("salesStatusName", "请选择");
    		listMap.add(map);
    		Map<String, Object> map1 = new HashMap<String, Object>();
    		map1.put("salesStatusCode", "正常");
    		map1.put("salesStatusName", "正常");
    		listMap.add(map1);
    		Map<String, Object> map2 = new HashMap<String, Object>();
    		map2.put("salesStatusCode", "异常");
    		map2.put("salesStatusName", "异常");
    		listMap.add(map2);
    		JSONArray json = JSONArray.fromObject(listMap); 
    		return json;
    		
    	}catch(Exception e){
    		logger.error("调用列表失败！",e);
    		throw new IllegalArgumentException(e);
    	}
	}
	
	/**
	 * @comment 查询姓名
	 * @author lichunyue
	 */
	@RequestMapping(value = "/selectUserNameMethod")
	@ResponseBody
	public JSONArray selectUserNameMethod(@RequestParam(value = "department", required = false) String department){
    	try{
    		Subject currStaff = SecurityUtils.getSubject();
    		RemoteStaffVo staff = (RemoteStaffVo) currStaff.getPrincipal();
        	RemoteStaffVo remoteStaffVo = swaggerService.employeeNo(staff.getEmployeeNo());
    		JSONArray json = customerCluesService.selectUserNameMethod(department,remoteStaffVo.getOrganizationVo().getParentVo().getCode());
    		return json;
    	}catch(Exception e){
    		throw new IllegalArgumentException(e);
    	}
	}
	
	/**
	 * @comment 查询分组
	 * @author lichunyue
	 */
	@RequestMapping(value = "/selectUserMethod")
	@ResponseBody
	public JSONArray selectUserMethod(){
    	try{
    		List<Map<String, Object>> listMap = new ArrayList<>();
    		Map<String, Object> map = new HashMap<>();
    		map.put("departmentCode", "");
    		map.put("departmentName", "请选择");
    		listMap.add(map);
    		Map<String, Object> map1 = new HashMap<>();
    		map1.put("departmentCode", "一组");
    		map1.put("departmentName", "一组");
    		listMap.add(map1);
    		Map<String, Object> map2 = new HashMap<>();
    		map2.put("departmentCode", "二组");
    		map2.put("departmentName", "二组");
    		listMap.add(map2);
    		Map<String, Object> map3 = new HashMap<>();
    		map3.put("departmentCode", "三组");
    		map3.put("departmentName", "三组");
    		listMap.add(map3);
    		Map<String, Object> map4 = new HashMap<>();
    		map4.put("departmentCode", "四组");
    		map4.put("departmentName", "四组");
    		listMap.add(map4);
    		Map<String, Object> map5 = new HashMap<>();
    		map5.put("departmentCode", "五组");
    		map5.put("departmentName", "五组");
    		listMap.add(map5);
    		JSONArray json = JSONArray.fromObject(listMap); 
    		return json;
    		
    	}catch(Exception e){
    		throw new IllegalArgumentException(e);
    	}
	}
	/**
     * 查询所选用户信息
     * 
     * @author yangzhiqiang
     * @date 2015年10月21日 下午2:24:41
     */
    @ResponseBody
    @RequestMapping(value = "/findUser")
    public Object findUser(Long id) {
        Assert.notNull(id, "id is null");
       
        return customerCluesService.findCustomerCluesMethod(id);
    }
    
    /**
     * 查询进件渠道,返回的是json类型的字符串
     * 
     * @param request
     * @return
     */
    @RequestMapping("queryChannel")
    @ResponseBody
    public String queryChannel() {
    	List<Map<String,Object>> list = customerCluesService.findChannl();
    	
        return JsonTools.fromObjectToJson(list);
    }
}
