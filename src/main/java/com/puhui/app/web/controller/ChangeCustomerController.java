package com.puhui.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.service.ChangeCustomerService;
import com.puhui.app.utils.CommonUtils;
import com.puhui.app.vo.QueryChangeCustomerVo;
import com.puhui.uc.api.service.RemoteLendAppUserCenterService;
import com.puhui.uc.vo.RemoteStaffVo;

/**
 * 更换销售绑定
 * @author yangzq
 *
 */
@Controller
@RequestMapping(value = "/changeCustomer")
public class ChangeCustomerController extends BaseController{
	
	@Autowired
	private ChangeCustomerService changeCustomerService;
	@Autowired
	private RemoteLendAppUserCenterService remoteLendAppUserCenterService;
	
	/**
	 * 页面跳转
	 * @author yangzq
	 * @return
	 */
	@RequestMapping("/goChangeCustomerMethod")
	public String goAccessoryResetMethod(){
		return "business/changeCustomer";
	}
	
	/**
	 * @comment 
	 * @author yangzq
	 * @return
	 */
	@RequestMapping(value = "/selectChangeCustomerMethod")
	@ResponseBody
	public Map<String, Object> selectCustomerCluesMethod(QueryChangeCustomerVo queryChangeCustomerVo){
		Subject currStaff = SecurityUtils.getSubject();
        RemoteStaffVo staff = (RemoteStaffVo) currStaff.getPrincipal();
        queryChangeCustomerVo.setStaffId(staff.getId());
        queryChangeCustomerVo.setStaffCode(staff.getOrganizationVo().getCode());
    	Map<String, Object> objMap = new HashMap<String, Object>();
    	try{
    		
    		objMap = changeCustomerService.selectChangeCustomerMethod(queryChangeCustomerVo);
    	}catch(Exception e){
    		System.out.println("查询附件失败");
    		throw new IllegalArgumentException(e);
    	}
    	return objMap;
	}
	
	/**
	 * @comment 绑定
	 * @author lichunyue
	 */
	@RequestMapping(value = "/updateBindingUserMethod")
	@ResponseBody
	public Object updateBindingUserMethod(@RequestParam Map map){
		
		 String selectUserName = (String) map.get("selectUserName");
		 List<Long> ids = CommonUtils.getLonglist(map.get("ids")); // appCustomerId
		 boolean falg = true;
		 Subject currStaff = SecurityUtils.getSubject();
	     RemoteStaffVo staff = (RemoteStaffVo) currStaff.getPrincipal();
	       
	     if(! "LEND_MANAGER".equals(staff.getPositionType())){
	    	 falg = changeCustomerService.validate(ids);
	    	 if(!falg) throw new RuntimeException("   “正常”的销售不可更换绑定     ");
	     }
    	try{
    		
    		 changeCustomerService.updateBindingUserMethod(ids,selectUserName);
    		 
    		 return true; 
    	}catch(Exception e){
    		System.out.println("绑定失败");
    		throw new IllegalArgumentException(e);
    	}
	}
	
	
	
	/**
	 * @comment 查询姓名
	 * @author lichunyue
	 */
	@RequestMapping(value = "/selectUserNameMethod")
	@ResponseBody
	public JSONArray selectUserNameMethod(@RequestParam(value = "oid", required = false) Long oid){
    	try{
    		JSONArray json = changeCustomerService.selectUserNameMethod(oid);
    		return json;
    	}catch(Exception e){
    		System.out.println("调用列表失败！");
    		throw new IllegalArgumentException(e);
    	}
	}

}
