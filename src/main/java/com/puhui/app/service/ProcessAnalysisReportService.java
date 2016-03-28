package com.puhui.app.service;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.ProcessAnalysisReport;

/**
 * @comment app全流程分析表接口
 * @author liwang
 * @time 2015年7月20日 上午10:26:34
 */
public interface ProcessAnalysisReportService {
	
	/**
	 * @comment app全流程分析表
	 * @returned List<ProcessAnalysisReport>
	 * @author liwang
	 * @time 2015年7月20日 上午10:45:09
	 * @param paramMap 查询参数
	 * @return
	 */
	public List<ProcessAnalysisReport> getProcessAnalysisReport(Map<String, Object> paramMap)throws Exception;
}
