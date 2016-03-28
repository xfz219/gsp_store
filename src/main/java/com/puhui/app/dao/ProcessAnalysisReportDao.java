package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.ProcessAnalysisReport;

/**
 * @comment app全流程分析表dao类
 * @author liwang
 * @time 2015年7月20日 上午10:47:38
 */
public interface ProcessAnalysisReportDao extends BaseDao<ProcessAnalysisReport>{
	
	/**
	 * @comment 通过开始时间和结束时间统计注册用户数
	 * @returned List<ProcessAnalysisReport>
	 * @author liwang
	 * @time 2015年7月20日 上午11:17:46
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<ProcessAnalysisReport> queryRegisteredCount(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * @comment 统计完成身份信息用户数
	 * @returned List<Map<String,Object>>
	 * @author liwang
	 * @time 2015年7月20日 下午3:31:40
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryAnnexStatusTypeCount(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * @comment 通过销售编号统计该销售下完成录入所有信息项件数
	 * @returned List<Map<String,Object>>
	 * @author liwang
	 * @time 2015年7月22日 下午3:22:17
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryRequestInputCompletionCount(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * @comment 通过销售编号统计该销售下销售质检完成件数
	 * @returned List<Map<String,Object>>
	 * @author liwang
	 * @time 2015年7月22日 下午3:39:51
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryRequestStateCount(Map<String, Object> paramMap)throws Exception;
}
