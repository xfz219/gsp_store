package com.puhui.app.utils;

/**
 * 功能描述: 地址工具类
 */
public class HttpUtils {

    private static final String PUHUI_LEND_DEDUCTION_URL = "http://d1.zuul.pub.puhuifinance.com:8765/puhui-lend-server";
    
    public static final String PUHUI_LEND_URL_ID = PUHUI_LEND_DEDUCTION_URL + "/api/v1/{appLendRequestId}/appLendRequest"; //通过app进件号获取进件信息
    public static final String PUHUI_LEND_URL_IDNO = PUHUI_LEND_DEDUCTION_URL + "/api/v1/lendRequestVos/{idNo}/idNo"; //通过身份证获取进件信息 
    public static final String PUHUI_LEND_URL_STATEUPDATE = PUHUI_LEND_DEDUCTION_URL + "/api/v1/lendRequests/update"; //状态变更通知
    public static final String PUHUI_LEND_URL_LOGIN = PUHUI_LEND_DEDUCTION_URL + "/api/uc/v1/staff/login"; //登陆认证
    public static final String PUHUI_LEND_URL_EMPLOYEENO = PUHUI_LEND_DEDUCTION_URL + "/api/uc/v1/staff/{employeeNo}/employeeNo"; //根据员工编号获取UC中用户信息
    public static final String PUHUI_LEND_URL_UCID = PUHUI_LEND_DEDUCTION_URL + "/api/uc/v1/staff/{id}/id"; //根据ID获取UC中用户信息
    public static final String PUHUI_LEND_URL_CHANGEPWD = PUHUI_LEND_DEDUCTION_URL + "/api/uc/v1/staff/{staffId}/changePwd"; //根据用户ID修改用户密码
    public static final String PUHUI_LEND_URL_LIKE = PUHUI_LEND_DEDUCTION_URL + "/api/uc/v1/org/{code}/like"; //根据机构code模糊查询机构
    public static final String PUHUI_LEND_URL_ORGID = PUHUI_LEND_DEDUCTION_URL + "/api/uc/v1/org/{id}/sub"; //根据机构ID获取下属机构信息
    public static final String PUHUI_LEND_URL_UCPAGE = PUHUI_LEND_DEDUCTION_URL + "/api/uc/v1/staff/page"; //根据用户信息查询用户分页列表
    
}
