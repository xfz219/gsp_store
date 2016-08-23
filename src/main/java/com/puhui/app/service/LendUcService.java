package com.puhui.app.service;

public interface LendUcService {
	
    /**
     * 根据登陆者 动态获取机构树
     */
    public Object getOrgTree(String organizationCode);
    
	
    /**
     * 获取机构门店
     * 
     * @author lichunyue
     * @date 2016年8月17日14:08:42
     */
    public Object getOrgTreeShop(String organizationCode);
    

    
}
