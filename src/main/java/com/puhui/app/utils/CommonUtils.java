package com.puhui.app.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.puhui.uc.vo.RemoteStaffVo;

/**
 * 监控系统常用工具类
 * @author xiaobowen
 *
 */
public class CommonUtils {
    /**
     * 获取当前登录用户
     * 
     * @author xiaobowen
     * @return
     */
    public static RemoteStaffVo getLoginStaff() {
        Subject currStaff = SecurityUtils.getSubject();
        RemoteStaffVo staff = (RemoteStaffVo) currStaff.getPrincipal();
        return staff;
    }
}
