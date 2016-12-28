package com.puhui.app.service;

public interface UpdateCustomerEntryStateService {
	/**
	 * 质回未上传
	 * @author lichunyue
	 * @return
	 */
	public void updateCustomerEntryState(long id);

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
	

}
