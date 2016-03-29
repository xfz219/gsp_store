package com.puhui.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.dao.AppUserToPromoteDao;
import com.puhui.app.service.CustomerCluesService;

@Service
public class CustomerCluesServiceImpl implements CustomerCluesService{
	
	@Autowired
	private AppUserToPromoteDao appUserToPromoteDao;
	/**
	 * @comment 线索查询
	 * @author lichunyue
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectCustomerCluesMethod(Map<String, Object> paramMap) throws Exception {
		List<Map<String, Object>> list = appUserToPromoteDao.selectCustomerCluesMethod(paramMap);
		return list;
	}

}
