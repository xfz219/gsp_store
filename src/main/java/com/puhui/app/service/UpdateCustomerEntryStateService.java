package com.puhui.app.service;

import java.util.Map;

public interface UpdateCustomerEntryStateService {
	/**
	 * 质回未上传
	 * @author lichunyue
	 * @return
	 */
	public void updateCustomerEntryState(Map<String, Object> map);

	/**
	 * @param id
	 */
	public void showChangeMobilePushDialog(long id);

	/**
	 * @param mobile
	 */
	public void showChangeMobileDelDialog(String mobile);

	/**
	 * @param id
	 */
	public void showChangeIdDelDialog(long id);

	/**
	 * 洗数据
	 */
	public void cleanStep();
	

}
