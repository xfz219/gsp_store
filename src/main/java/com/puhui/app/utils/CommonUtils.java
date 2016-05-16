package com.puhui.app.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
    
    public static String getString(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
    
    
    public static List<Long> getLonglist(Object obj){
    	String str = getString(obj);
    	List<Long> list = new ArrayList<Long>();
    	if(StringUtils.isNotBlank(str)){
			if(str.contains(",")){
				String[] strArr = StringUtils.split(str, ",");
				for(int i=0;i<strArr.length;i++){
					list.add(Long.valueOf(strArr[i]));
				}
			}else{
				list.add(Long.valueOf(str));
			}
		}
    	return list;
    }
}
