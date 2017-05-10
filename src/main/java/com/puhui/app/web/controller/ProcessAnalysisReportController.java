package com.puhui.app.web.controller;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.excel.ProcessAnalysisReportExcelUtil;
import com.puhui.app.po.ProcessAnalysisReport;
import com.puhui.app.service.ProcessAnalysisReportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @comment app全流程分析表
 * @author liwang
 * @time 2015年7月20日 下午12:28:04
 */
@Controller
@RequestMapping(value = "/processAnalysisReport")
public class ProcessAnalysisReportController {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessAnalysisReportController.class);
	
	@Resource
	private ProcessAnalysisReportService processAnalysisReportService;
	
	/**
	 * @comment app全流程分析表
	 * @returned Object
	 * @author liwang
	 * @time 2015年7月23日 上午10:35:35
	 * @param pageMap 分页参数
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param salesNo 员工编号
	 * @return
	 */
    @RequestMapping(value = "/getProcessAnalysisReportMethod")
    public Object getProcessAnalysisReportMethod(@RequestParam Map<String, Object> pageMap, 
    		@RequestParam(value = "startTime", required = false) String startTime,
    		@RequestParam(value = "endTime", required = false) String endTime,
    		@RequestParam(value = "salesNo", required = false) String salesNo) {
    	
    	logger.info("in ProcessAnalysisReportController.getProcessAnalysisReportMethod(){}--start");
    	Map<String, Object> objMap = new HashMap<>();
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
        	paramMap.put("startTime", startTime);
        	paramMap.put("endTime", endTime);
        	paramMap.put("salesNo", salesNo);
        	
        	List<ProcessAnalysisReport> list = processAnalysisReportService.getProcessAnalysisReport(paramMap);
        	
        	objMap.put("total", page.getTotalCount());
        	objMap.put("rows", list);
    	}catch(Exception e){
    		logger.error("app全流程分析表系统异常：",e);
    		throw new IllegalArgumentException(e);
    	}

    	logger.info("in ProcessAnalysisReportController.getProcessAnalysisReportMethod(){}--end");
    	return objMap;
    }

    /**
     * @comment app全流程分析表导出
     * @returned void
     * @author liwang
     * @time 2015年7月23日 下午12:33:11
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param salesNo 员工编号
     * @param response
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/exportProcessAnalysisReportExcel")
    public void exportProcessAnalysisReportExcel(@RequestParam(value = "startTime", required = false) String startTime,
    		@RequestParam(value = "endTime", required = false) String endTime,
    		@RequestParam(value = "salesNo", required = false) String salesNo,
    		HttpServletResponse response,HttpServletRequest request) {
    	logger.info("in ProcessAnalysisReportController.exportProcessAnalysisReportExcel(){}--start");
    	try{
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("startTime", startTime);
        	paramMap.put("endTime", endTime);
        	paramMap.put("salesNo", salesNo);
        	List<ProcessAnalysisReport> list = processAnalysisReportService.getProcessAnalysisReport(paramMap);
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String excelName = "APP全流程分析表_" + sdf.format(new Date());
            HSSFWorkbook workbook = new HSSFWorkbook();
            workbook = ProcessAnalysisReportExcelUtil.creatExcel(list);
            response.setContentType("application/vnd.ms-excel");
            String fileName = new String(excelName.getBytes("utf-8"), "ISO-8859-1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            workbook.write(response.getOutputStream());
    	}catch(Exception e){
    		logger.error("app全流程分析表导出系统异常：",e);
    		throw new IllegalArgumentException(e);
    	}
    	logger.info("in ProcessAnalysisReportController.exportProcessAnalysisReportExcel(){}--end");
    }
    
}
