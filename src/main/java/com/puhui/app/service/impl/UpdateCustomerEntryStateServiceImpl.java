package com.puhui.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.manager.UpdateCustomerEntryStateManager;
import com.puhui.app.service.UpdateCustomerEntryStateService;



@Service
public class UpdateCustomerEntryStateServiceImpl implements UpdateCustomerEntryStateService{
	
	@Autowired
	private UpdateCustomerEntryStateManager updateCustomerEntryStateManager;
	
	/**
	 * 质回未上传
	 * @author lichunyue
	 * @return
	 */
	@Override
	public void updateCustomerEntryState(long id){
		updateCustomerEntryStateManager.updateCustomerEntryState(id);
	}

	@Override
	public void showChangeMobilePushDialog(long id) {
		updateCustomerEntryStateManager.showChangeMobilePushDialog(id);
	}

	@Override
	public void showChangeMobileDelDialog(String mobile) {
		updateCustomerEntryStateManager.showChangeMobileDelDialog(mobile);
	}

}
