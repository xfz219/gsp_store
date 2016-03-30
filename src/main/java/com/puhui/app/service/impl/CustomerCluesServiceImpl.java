package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.dao.AppUserToPromoteDao;
import com.puhui.app.po.AppUserToPromote;
import com.puhui.app.service.CustomerCluesService;

@Service
public class CustomerCluesServiceImpl implements CustomerCluesService{
	
	@Autowired
	private AppUserToPromoteDao appUserToPromoteDao;
	@Autowired
	private AppCustomerDao appCustomerDao;
	/**
	 * @comment 线索查询
	 * @author lichunyue
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectCustomerCluesMethod(Map<String, Object> paramMap) throws Exception {
		List<AppUserToPromote> autpList = appUserToPromoteDao.selectCustomerCluesMethod(paramMap);
		List<Map<String, Object>> list =  new ArrayList<Map<String, Object>>();
		for(AppUserToPromote autp : autpList){
    		Map<String, Object> autpMap = new HashMap<String, Object>();
    		autpMap.put("name", autp.getName());
    		autpMap.put("mobile", autp.getMobile());
    		autpMap.put("city", autp.getCity());
    		autpMap.put("branch", autp.getBranch());
    		autpMap.put("salesName", autp.getSalesName());
    		autpMap.put("salesNo", autp.getSalesNo());
    		Map<String, Object> map= appCustomerDao.getMobileMethod(autp.getMobile());
    		autpMap.put("registered", map == null ? "未注册" : "已注册");
    		autpMap.put("sales", autp.getSalesNo() == null ? "否" : "是");
    		autpMap.put("salesStatus", autp.getBranchCode());
    		list.add(autpMap);
    	}
		return list;
	}

}
