package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.ConsumingAnalysisReport;

/**
 * @comment app耗时分析表dao类
 * @author liwang
 * @time 2015年7月21日 下午6:13:56
 */
public interface ConsumingAnalysisReportDao extends BaseDao<ConsumingAnalysisReport>{

	/**
	 * @comment 通过开始时间结束时间查询所有进件
	 * @returned List<ConsumingAnalysisReport>
	 * @author liwang
	 * @time 2015年7月21日 下午6:14:25
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<ConsumingAnalysisReport> queryLendRequest(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * @comment 通过进件号查询所有填写信息及附件耗时
	 * @returned List<Map<String,Object>>
	 * @author liwang
	 * @time 2015年7月22日 上午10:06:46
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryAnnexEntryTime(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * @comment 通过进件号查询贷款需求
	 * @returned List<Map<String,Object>>
	 * @author liwang
	 * @time 2015年7月22日 下午4:11:22
	 * @param paramMap 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryLoanDemand(Map<String, Object> paramMap)throws Exception;
}
