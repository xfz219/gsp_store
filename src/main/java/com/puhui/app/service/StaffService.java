package com.puhui.app.service;

import com.puhui.app.vo.ReturnEntity;

public interface StaffService {
    /**
     * 修改密码
     * 
     * @author xiaobowen
     * @param pwd
     * @param oldPwd
     */
    public ReturnEntity<Object> changePwd(String pwd, String oldPwd);
}
