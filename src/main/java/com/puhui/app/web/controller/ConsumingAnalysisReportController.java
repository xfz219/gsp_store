package com.puhui.app.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.excel.ConsumingAnalysisReportExcelUtil;
import com.puhui.app.po.ConsumingAnalysisReport;
import com.puhui.app.service.ConsumingAnalysisReportService;


/**
 * @comment APP客户耗时分析表
 * @author liwang
 * @time 2015年7月21日 下午4:26:05
 */
@Controller
@RequestMapping(value = "/consumingAnalysisReport")
public class ConsumingAnalysisReportController {
	
	private static Logger logger = LoggerFactory.getLogger(ConsumingAnalysisReportController.class);
	
	@Resource
	private ConsumingAnalysisReportService consumingAnalysisReportService;
	
	/**
	 * @comment APP客户耗时分析表查询
	 * @returned Object
	 * @author liwang
	 * @time 2015年7月23日 上午10:50:35
	 * @param pageMap 分页参数
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
    @RequestMapping(value = "/getConsumingAnalysisReportMethod")
    public Object getConsumingAnalysisReportMethod(@RequestParam Map<String, Object> pageMap,
    		@RequestParam(value = "startTime", required = false) String startTime,
    		@RequestParam(value = "endTime", required = false) String endTime) {
    	
    	logger.info("in ConsumingAnalysisReportController.getConsumingAnalysisReportMethod(){}--start");
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
        	paramMap.put("startTime", startTime);
        	paramMap.put("endTime", endTime);
        	
        	List<ConsumingAnalysisReport> list = consumingAnalysisReportService.getConsumingAnalysisReport(paramMap);
        	objMap.put("total", page.getTotalCount());
        	objMap.put("rows", list);
    	}catch(Exception e){
    		logger.error("app客户耗时分析表系统异常：",e);
    		throw new IllegalArgumentException(e);
    	}

    	logger.info("in ConsumingAnalysisReportController.getConsumingAnalysisReportMethod(){}--end");
    	return objMap;
    }

    /**
     * @comment APP客户耗时分析表导出
     * @returned void
     * @author liwang
     * @time 2015年7月23日 下午2:34:03
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param response
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/exportConsumingAnalysisReportExcel")
    public void exportConsumingAnalysisReportExcel(@RequestParam(value = "startTime", required = false) String startTime,
    		@RequestParam(value = "endTime", required = false) String endTime,
    		HttpServletResponse response,HttpServletRequest request) {
    	logger.info("in ConsumingAnalysisReportController.exportConsumingAnalysisReportExcel(){}--start");
    	try{
            //查询条件参数
            Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("startTime", startTime);
        	paramMap.put("endTime", endTime);
        	List<ConsumingAnalysisReport> list = consumingAnalysisReportService.getConsumingAnalysisReport(paramMap);
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String excelName = "APP耗时分析表_" + sdf.format(new Date());
            HSSFWorkbook workbook = new HSSFWorkbook();
            workbook = ConsumingAnalysisReportExcelUtil.creatExcel(list);
            response.setContentType("application/vnd.ms-excel");
            String fileName = new String(excelName.getBytes("utf-8"), "ISO-8859-1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            workbook.write(response.getOutputStream());
    	}catch(Exception e){
    		logger.error("app耗时分析表导出系统异常：",e);
    		throw new IllegalArgumentException(e);
    	}
    	logger.info("in ConsumingAnalysisReportController.exportConsumingAnalysisReportExcel(){}--end");
    }
}
