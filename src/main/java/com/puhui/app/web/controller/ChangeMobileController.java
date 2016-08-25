package com.puhui.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jpush.api.utils.StringUtils;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.po.AppUserTokenChangeRecord;
import com.puhui.app.service.ChangeMobileService;
import com.puhui.app.vo.AppUserTokenChangeRecordVo;
import com.puhui.app.vo.ReturnEntity;

/**
 * @comment 门店销售人员手机设备变更
 * @author liwang
 * @time 2015年8月3日 上午10:05:21
 */
@Controller
@RequestMapping(value = "/changeMobile")
public class ChangeMobileController {
	
	private static Logger logger = LoggerFactory.getLogger(ConsumingAnalysisReportController.class);
	
	@Resource
	private ChangeMobileService changeMobileService;
	
	
	/**
	 * @comment
	 * @returned String
	 * @author liwang
	 * @time 2015年8月3日 上午10:07:11
	 * @return
	 */
	@RequestMapping("/goChangeMobileMethod")
	public String goChangeMobileMethod(){
		logger.info("in ChangeMobileController.goChangeMobileMethod(){}");
		return "business/changeMobile";
	}
	
	/**
	 * @comment 分页查询销售人员手机设备变更记录
	 * @returned Object
	 * @author liwang
	 * @time 2015年8月3日 上午11:26:16
	 * @param pageMap 分页条件
	 * @param salesNo 员工编号
	 * @return
	 */
	@RequestMapping("/queryChangeMobileRecordMethod")
	public Object queryChangeMobileRecordMethod(@RequestParam Map<String, Object> pageMap,
			@RequestParam(value = "salesNo", required = false) String salesNo){
		logger.info("in ChangeMobileController.queryChangeMobileRecordMethod(){}--start");
		
		Map<String, Object> objMap = new HashMap<String, Object>();
		try{
			if (pageMap.get("page") == null||pageMap.get("rows") == null) {
    			throw new IllegalArgumentException("分页参数为空。");
            }
			int pageNo = Integer.valueOf(pageMap.get("page").toString());// 当前页
    		int pageSize= Integer.valueOf(pageMap.get("rows").toString());// 当前页大小
            Page page = Page.getPage(pageNo,pageSize);
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("page", page);
            paramMap.put("salesNo", salesNo);
            
			List<AppUserTokenChangeRecord> dataList = changeMobileService.queryAppUserTokenChangeRecord(paramMap);
			objMap.put("total", page.getTotalCount());
        	objMap.put("rows", dataList);
		}catch(Exception e){
			logger.error("查询销售人员手机设备变更记录异常：",e);
    		throw new IllegalArgumentException(e);
		}
		
		logger.info("in ChangeMobileController.queryChangeMobileRecordMethod(){}--end");
		return objMap;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/changeMobileMethod")
	public Object changeMobileMethod(@RequestParam(value = "salesNo", required = true) String salesNo){
		logger.info("in ChangeMobileController.changeMobileMethod(){}--start");
		
		ReturnEntity returnEntity = new ReturnEntity(true, "设备变更成功！");
        if (StringUtils.isEmpty(salesNo)) {
            returnEntity = new ReturnEntity(false, "员工编号为空！");
        } else{
        	try {
            		this.changeMobileService.changeMobile(salesNo);
            } catch (Exception e) {
                logger.error("销售人员手机设备变更失败", e);
                returnEntity = new ReturnEntity(false, "销售人员手机设备变更失败！");
            }
        }
        
        logger.info("in ChangeMobileController.changeMobileMethod(){}--end");
        return returnEntity;
	}
}
