package com.puhui.app.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.service.UpdateCustomerEntryStateService;
import com.puhui.app.utils.LendAesUtil;

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
			HttpServletResponse response,HttpServletRequest request){
		try{
			updateCustomerEntryStateService.updateCustomerEntryState(id);
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
	public boolean showChangeMobileDelDialog(@RequestParam(value = "id" , required = false) long id,
			HttpServletResponse response,HttpServletRequest request){
		String mobile = LendAesUtil.encrypt(String.valueOf(id));
		try{
			updateCustomerEntryStateService.showChangeMobileDelDialog(mobile);
		}catch(Exception e){
			logger.error("系统异常：",e);
    		throw new IllegalArgumentException(e);
		}
		return true;
		}
	}
