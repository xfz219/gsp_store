package com.puhui.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.puhui.app.po.AppUserToken;

public interface AppUserTokenDao extends BaseDao<AppUserToken>{

	public List<AppUserToken> getAppUserToken(AppUserToken appUserToken);
	
}
