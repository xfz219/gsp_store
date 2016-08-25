package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.common.constant.Select;
import com.puhui.app.dao.ChangeMobileDao;
import com.puhui.app.manager.ChangeMobileManager;
import com.puhui.app.po.AppUserTokenChangeRecord;
import com.puhui.app.service.ChangeMobileService;
import com.puhui.app.vo.AppUserTokenChangeRecordVo;

/**
 * @comment 销售人员设备变更记录ServiceImpl
 * @author liwang
 * @time 2015年8月3日 上午11:15:05
 */
@Service
public class ChangeMobileServiceImpl implements ChangeMobileService{
	@Autowired
	private ChangeMobileDao changeMobileDao;
	@Autowired
	private ChangeMobileManager changeMobileManager;
	
	@Override
	public List<AppUserTokenChangeRecord> queryAppUserTokenChangeRecord(Map<String, Object> paramMap)
			throws Exception {
		List<AppUserTokenChangeRecord> poList = new ArrayList<AppUserTokenChangeRecord>();
		List<AppUserTokenChangeRecordVo> voList = changeMobileDao.queryAppUserTokenChangeRecord(paramMap);
		if(CollectionUtils.isNotEmpty(voList)){
			for(AppUserTokenChangeRecordVo vo : voList){
				AppUserTokenChangeRecord po = new AppUserTokenChangeRecord();
				po.setId(vo.getId());
				po.setPid(vo.getPid());
				po.setSalesNo(vo.getSalesNo());
				po.setUpdateTime(vo.getUpdateTime());
				po.setClientTypeName(Select.getInstance().clientTypeMap().get(vo.getClientType()));
				poList.add(po);
			}
		}
		return poList;
	}

	@Override
	public List<AppUserTokenChangeRecordVo> queryAppUserToken(String salesNo)
			throws Exception {
		return this.changeMobileDao.queryAppUserToken(salesNo);
	}

	@Override
	public void changeMobile(String salesNo) throws Exception {
			changeMobileManager.doChangeMobile(salesNo);
	}

}
