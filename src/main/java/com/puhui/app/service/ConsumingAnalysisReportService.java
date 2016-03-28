package com.puhui.app.service;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.ConsumingAnalysisReport;

/**
 * @comment APP耗时分析表接口
 * @author liwang
 * @time 2015年7月21日 下午4:31:28
 */
public interface ConsumingAnalysisReportService {
	
	/**
	 * @comment 获取APP耗时分析表数据
	 * @returned List<ConsumingAnalysisReport>
	 * @author liwang
	 * @time 2015年7月21日 下午4:31:16
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<ConsumingAnalysisReport> getConsumingAnalysisReport(Map<String, Object> paramMap)throws Exception;
}
