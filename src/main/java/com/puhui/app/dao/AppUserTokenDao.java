package com.puhui.app.dao;

import com.puhui.app.po.AppUserToken;

import java.util.List;

public interface AppUserTokenDao extends BaseDao<AppUserToken>{

	public List<AppUserToken> getAppUserToken(AppUserToken appUserToken);

	List<AppUserToken> getAppUserTokenDistrictCodeList(String districtCode);
	
}
