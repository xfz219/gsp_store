package com.puhui.app.web.controller;

import com.puhui.app.service.UpdateCustomerEntryStateService;
import com.puhui.app.utils.LendAesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @comment 修改客户录件状态
 * @author lichunyue
 */
@Controller
@RequestMapping(value = "/updateCustomerEntryState")
public class UpdateCustomerEntryStateController {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessAnalysisReportController.class);
	@Autowired
	private UpdateCustomerEntryStateService updateCustomerEntryStateService;
	
	/**
	 * 页面跳转
	 * @author lichunyue
	 * @return
	 */
	@RequestMapping("/goUpdateCustomerEntryStateMethod")
	public String goUpdateCustomerEntryStateMethod(){
		return "business/updateCustomerEntryState";
	}
	
	/**
	 * 质回拒贷
	 * @author lichunyue
	 * @return 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateCustomerEntryStateMethod")
	public boolean updateCustomerEntryStateMethod(@RequestParam(value = "id" , required = false) long id,
												  @RequestParam(value = "state" , required = false) String state,
												  @RequestParam(value = "auditState" , required = false) String auditState,
			HttpServletResponse response,HttpServletRequest request){
		try{
			Map<String, Object> map = new HashMap<>();
			map.put("id",id);
			map.put("state",state);
			map.put("auditState",auditState);
			updateCustomerEntryStateService.updateCustomerEntryState(map);
		}catch(Exception e){
			logger.error("系统异常：",e);
    		throw new IllegalArgumentException(e);
		}
		return true;
		}
	
	/**
	 * 质回未上传
	 * @author lichunyue
	 * @return 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showChangeMobilePushDialog")
	public boolean showChangeMobilePushDialog(@RequestParam(value = "id" , required = false) long id,
			HttpServletResponse response,HttpServletRequest request){
		try{
			updateCustomerEntryStateService.showChangeMobilePushDialog(id);
		}catch(Exception e){
			logger.error("系统异常：",e);
    		throw new IllegalArgumentException(e);
		}
		return true;
		}
	
	/**
	 * 删除客户信息
	 * @author lichunyue
	 * @return 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showChangeMobileDelDialog")
	public boolean showChangeMobileDelDialog(@RequestParam(value = "mobile" , required = false) String mobile,
			HttpServletResponse response,HttpServletRequest request){
		try{
			mobile = LendAesUtil.encrypt(mobile);
			updateCustomerEntryStateService.showChangeMobileDelDialog(mobile);
		}catch(Exception e){
			logger.error("系统异常：",e);
    		throw new IllegalArgumentException(e);
		}
		return true;
	}
	
	/**
	 * 删除获客信息
	 * @author lichunyue
	 * @return 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showChangeIdDelDialog")
	public boolean showChangeIdDelDialog(@RequestParam(value = "id" , required = false) long id,
			HttpServletResponse response,HttpServletRequest request){
		try{
			updateCustomerEntryStateService.showChangeIdDelDialog(id);
		}catch(Exception e){
			logger.error("系统异常：",e);
    		throw new IllegalArgumentException(e);
		}
		return true;
		}

	/**
	 * 洗数据
	 * @author lichunyue
	 * @return 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateAppUserToPromote")
	public boolean updateAppUserToPromote(){
		try{
			updateCustomerEntryStateService.updateAppUserToPromote();
		}catch(Exception e){
			logger.error("系统异常：",e);
			throw new IllegalArgumentException(e);
		}
		return true;
		}
	}
