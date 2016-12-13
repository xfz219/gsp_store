package com.puhui.app.dao;

import java.util.List;
import java.util.Map;

import com.puhui.app.po.AppLendRequest;

public interface AppLendRequestDao extends BaseDao{

	public void updateCustomerEntryState(long id);
	
	public List<String> selectAppLendRequestMethod(Map<String,Object> map);
	
	public void updateCustomerSalesNo(Map<String,Object> map);
	/**
     * 获取AppLendRequest
     * @param CustomerId
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
}
