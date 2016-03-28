package com.puhui.app.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puhui.app.dao.AppLendRequestDao;

@Component
public class UpdateCustomerEntryStateManager {

	@Autowired
	private AppLendRequestDao appLendRequestDao;
	
	public void updateCustomerEntryState(long id) {
		appLendRequestDao.updateCustomerEntryState(id);
	}
	
	

}
