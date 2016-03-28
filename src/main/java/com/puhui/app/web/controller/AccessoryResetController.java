package com.puhui.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.common.constant.Select;
import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.service.AccessoryResetService;

/**
 * @comment 附件状态查询
 * @author lichunyue
 */
@Controller
@RequestMapping(value = "/accessoryReset")
public class AccessoryResetController {
	
	@Autowired
	private AccessoryResetService accessoryResetServer;
	
	
	/**
	 * 页面跳转
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping("/goAccessoryResetMethod")
	public String goAccessoryResetMethod(){
		return "business/accessoryReset";
	}
	
	/**
	 * @comment 附件查询（进件号、附件类型）
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping(value = "/selectAccessoryMethod")
	@ResponseBody
	public Object selectAccessory(@RequestParam Map<String, Object> pageMap, 
    		@RequestParam(value = "appLendRequestId", required = false) String appLendRequestId,
    		@RequestParam(value = "type", required = false) String type){
    	Map<String, Object> objMap = new HashMap<String, Object>();
    	try{
    		int pageNo = Integer.valueOf(pageMap.get("page").toString());// 当前页
    		int pageSize= Integer.valueOf(pageMap.get("rows").toString());// 当前页大小
            Page page = Page.getPage(pageNo,pageSize);
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>(); 
        	paramMap.put("page", page);
        	paramMap.put("appLendRequestId", appLendRequestId);
        	paramMap.put("type", type);
        	
        	List<Map<String, Object>> list = accessoryResetServer.getAccessoryResetServer(paramMap);
        	
        	objMap.put("total", page.getTotalCount());
        	objMap.put("rows", list);
    	}catch(Exception e){
    		System.out.println("查询附件失败");
    		throw new IllegalArgumentException(e);
    	}
    	return objMap;
	}
	
	/**
	 * @comment 重置获取状态与轮询次数
	 * @author lichunyue
	 */
	@RequestMapping(value = "/updateAccessory")
	@ResponseBody
	public void updateAccessory(@RequestParam(value = "app_lend_request_id", required = false) int appLendRequestId,
    		@RequestParam(value = "type", required = false) String type){
    	try{
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>(); 
        	paramMap.put("appLendRequestId", appLendRequestId);
        	paramMap.put("type", Select.getInstance().checkType().get(type));
        	paramMap.put("status", 5);
        	paramMap.put("queryNumber", 1);
        	
        	accessoryResetServer.updateAccessoryServer(paramMap);
        	
    	}catch(Exception e){
    		System.out.println("重置失败");
    		throw new IllegalArgumentException(e);
    	}
	}
}
