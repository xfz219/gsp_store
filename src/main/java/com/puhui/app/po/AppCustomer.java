package com.puhui.app.po;

import java.util.Date;

import com.puhui.app.po.BaseBean;

/**
 * 客户
 * 
 * @author xiaobowen
 */
public class AppCustomer extends BaseBean {
    private static final long serialVersionUID = 150702645541396425L;
    
    private Long id;

    /**
     * 客户经理员工编号
     * 
     * @author gaozhenbao
     */
    private Long customerServiceManagerNumber;

    /**
     * 客户经理员姓名
     * 
     * @author gaozhenbao
     */
    private String salesName;

    /**
     * 客户经理员电话
     * 
     * @author gaozhenbao
     */
    private String salesMobile;

    /**
     * 客户经理员工编号
     * 
     * @author lichunyue
     */
    private String salesNo;

    /**
     * 用户名
     * 
     * @author xiaobowen
     */
    private String customerName;
    /**
     * 身份证
     * 
     * @author xiaobowen
     */
    private String idNo;
    /**
     * 密码
     * 
     * @author xiaobowen
     */
    private String customerPassword;
    /**
     * 邮箱
     * 
     * @author xiaobowen
     */
    private String email;
    /**
     * 手机号
     * 
     * @author xiaobowen
     */
    private String mobile;

    /**
     * 是否启用
     * 
     * @author xiaobowen
     */
    private Integer isEnable;
    /**
     * 性别（1男2女）
     * 
     * @author lichunyue
     */
    private Integer gender;
    /**
     * 学历
     * 
     * @author lichunyue
     */
    private Integer education;
    /**
     * 1上班族2经营者
     * 
     * @author lichunyue
     */
    private Integer occupationNature;
    /**
     * 婚姻状况
     * 
     * @author lichunyue
     */
    private Integer marriage;
    /**
     * 有无子女
     * 
     * @author lichunyue
     */
    private Integer isExistChildren;
    /**
     * 子女数量
     * 
     * @author lichunyue
     */
    private Integer childrenNumber;

    /**
     * 本地有无房产
     * 
     * @author lichunyue
     */
    private Integer isProvideHouseProperty;
    /**
     * 公积金是否缴纳：0：否 1：是
     * 
     * @author lichunyue
     */
    private Integer accumulationFund;
    /**
     * 工资发放形式
     * 
     * @author lichunyue
     */
    private Integer salaryGetForm;
    /**
     * 禁用时间
     * 
     * @author xiaobowen
     */
    private Date disableTime;
    /**
     * 创建时间
     * 
     * @author xiaobowen
     */
    private Date createTime;
    /**
     * 更新时间
     * 
     * @author xiaobowen
     */
    private Date updateTime;
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
    /**
     * 原因(1:黑名单/2:非用户群)
     */
    private int reason;
    /**
     * 登陆id
     */
    private Long logInId;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(Integer accumulationFund) {
        this.accumulationFund = accumulationFund;
    }

    public Integer getSalaryGetForm() {
        return salaryGetForm;
    }

    public void setSalaryGetForm(Integer salaryGetForm) {
        this.salaryGetForm = salaryGetForm;
    }

    public Integer getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public void setIsProvideHouseProperty(Integer isProvideHouseProperty) {
        this.isProvideHouseProperty = isProvideHouseProperty;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getOccupationNature() {
        return occupationNature;
    }

    public void setOccupationNature(Integer occupationNature) {
        this.occupationNature = occupationNature;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public Integer getIsExistChildren() {
        return isExistChildren;
    }

    public void setIsExistChildren(Integer isExistChildren) {
        this.isExistChildren = isExistChildren;
    }

    public Integer getIsProvideHouseProperty() {
        return isProvideHouseProperty;
    }

    public void setIsProvideHousePropertyInteger(Integer isProvideHouseProperty) {
        this.isProvideHouseProperty = isProvideHouseProperty;
    }

    private Date appLendRequestCreateTime;

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public Long getCustomerServiceManagerNumber() {
        return customerServiceManagerNumber;
    }

    public void setCustomerServiceManagerNumber(Long customerServiceManagerNumber) {
        this.customerServiceManagerNumber = customerServiceManagerNumber;
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

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
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

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAppLendRequestCreateTime() {
        return appLendRequestCreateTime;
    }

    public void setAppLendRequestCreateTime(Date appLendRequestCreateTime) {
        this.appLendRequestCreateTime = appLendRequestCreateTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getShopCode() {
        return shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

	public Long getLogInId() {
		return logInId;
	}

	public void setLogInId(Long logInId) {
		this.logInId = logInId;
	}

}
