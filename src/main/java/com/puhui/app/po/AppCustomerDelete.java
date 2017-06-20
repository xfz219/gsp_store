package com.puhui.app.po;

import java.util.Date;

/**
 * 客户
 * 
 * @author lcy
 */
public class AppCustomerDelete extends BaseBean {
    private static final long serialVersionUID = 150702645546425L;
    
    /**
     * uid
     */
    private Long appCustomerId;

    /**
     * 客户经理员姓名
     */
    private String salesName;

    /**
     * 客户经理员电话
     */
    private String salesMobile;

    /**
     * 客户经理员工编号
     */
    private String salesNo;

    /**
     * 用户名
     */
    private String customerName;
    /**
     * 身份证
     */
    private String idNo;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 所属门店编号
     */
    private String shopCode;
    /**
     * 所属门店名称
     */
    private String shopName;
    /**
     * 所属大区编号
     */
    private String districtCode;
    /**
     * 所属大区名称
     */
    private String districtName;

    public Long getAppCustomerId() {
        return appCustomerId;
    }

    public void setAppCustomerId(Long appCustomerId) {
        this.appCustomerId = appCustomerId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getSalesMobile() {
        return salesMobile;
    }

    public void setSalesMobile(String salesMobile) {
        this.salesMobile = salesMobile;
    }

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
