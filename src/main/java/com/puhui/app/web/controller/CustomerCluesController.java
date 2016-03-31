package com.puhui.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.service.CustomerCluesService;

/**
 * @comment 客户线索
 * @author lichunyue
 */
@Controller
@RequestMapping(value = "/customerClues")
public class CustomerCluesController {
	
	@Autowired
	private CustomerCluesService customerCluesService;
	
	
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
    		@RequestParam(value = "branchCode", required = false) String branchCode,
    		@RequestParam(value = "salesName", required = false) String salesName,
    		@RequestParam(value = "salesNo", required = false) String salesNo,
    		@RequestParam(value = "salesStatus", required = false) String salesStatus){
		
    	Map<String, Object> objMap = new HashMap<String, Object>();
    	try{
//    		Subject currStaff = SecurityUtils.getSubject();
//            Staff staff = (Staff) currStaff.getPrincipal();
    		int pageNo = Integer.valueOf(pageMap.get("page").toString());// 当前页
    		int pageSize= Integer.valueOf(pageMap.get("rows").toString());// 当前页大小
            Page page = Page.getPage(pageNo,pageSize);
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>(); 
        	paramMap.put("page", page);
        	paramMap.put("radio", radio);
        	paramMap.put("name", name);
        	paramMap.put("mobile", mobile);
        	paramMap.put("branchCode", branchCode);
        	paramMap.put("salesName", salesName);
        	paramMap.put("salesNo", salesNo);
        	paramMap.put("salesStatus", salesStatus);
        	List<Map<String, Object>> autpList = customerCluesService.selectCustomerCluesMethod(paramMap);
        	objMap.put("total", page.getTotalCount());
        	objMap.put("rows", autpList);
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
	@RequestMapping(value = "/bindingUser")
	@ResponseBody
	public void bindingUser(
			@RequestParam(value = "id", required = false) int id,
    		@RequestParam(value = "selectUserName", required = false) String selectUserName){
    	try{
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>(); 
        	
//        	accessoryResetServer.updateAccessoryServer(paramMap);
        	
    	}catch(Exception e){
    		System.out.println("绑定失败");
    		throw new IllegalArgumentException(e);
    	}
	}
}
