package com.puhui.app.dao;

import java.util.List;

import com.puhui.app.po.AppCustomerToken;

public interface AppCustomerTokenDao extends BaseDao<AppCustomerToken>{
    /**
     * 获取AppCustomerToken
     */
    public List<AppCustomerToken> getAppCustomerToken(AppCustomerToken appCustomerToken);
    
}
