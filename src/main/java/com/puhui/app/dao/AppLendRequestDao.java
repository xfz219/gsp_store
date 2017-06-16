package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.puhui.app.po.AppLendRequest;

public interface AppLendRequestDao extends BaseDao{

	public void updateCustomerEntryState(Map<String, Object> map);
	
	public List<String> selectAppLendRequestMethod(Map<String,Object> map);
	
	public void updateCustomerSalesNo(Map<String,Object> map);
	/**
     * 获取AppLendRequest
     * @return
     */
	public AppLendRequest getAppLendRequestByCustomerId(Long appCustomerId);

	/**
	 * @param id
	 */
	public void showChangeMobilePushDialog(long id);

	/**
	 * @param mobile
	 */
	public void showChangeMobileDelDialog(String mobile);
	
	/**
	 * @param mobile
	 */
	public void showChangeMobileDelLoginDialog(String mobile);

	/**
	 * @param id
	 */
	public void showChangeIdDelDialog(long id);
	
	/**
	 * 
	 */
	public List<Map<String,Object>> getAppUserToPromote();

	/**
	 * 清洗数据
	 */
	void cleanStep();

	/**
	 * 调查问卷查询当前
	 * @param id
	 */
	boolean questionnaireControl(@Param("id")long id);

	/**
	 * 更新调查问卷状态
	 * @param id
	 */
	void updateQuestionnaire(@Param("id")long id, @Param("questionnaire")boolean questionnaire);
}
