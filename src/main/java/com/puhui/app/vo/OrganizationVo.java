/**
 * Copyright(c) 2013-2015 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.puhui.app.vo;

import java.io.Serializable;

import com.puhui.uc.vo.RemoteStaffVo;

/**
 * @author yangzq
 * @see RemoteStaffVo
 */
public class OrganizationVo implements Serializable {

    private static final long serialVersionUID = -6753395230708136181L;

    private Long id;

    private String name;

    private String pName;
    
    private Long pid;
    
    /**
     * 类型
     */
    private String organizationType;
    /**
     * 机构编码
     */
    private String code;

    /**
     * 地区编号+门店编号
     */
    private String areaShopCode;

    /**
     * 门店所在区域名字
     */
    private String areaName;

    public OrganizationVo() {
        super();
    }

    public OrganizationVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OrganizationVo(Long id, String name, Long pid, String pName, String organizationType,
            String code) {
        super();
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.pName = pName;
        this.organizationType = organizationType;
        this.code = code;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAreaShopCode() {
		return areaShopCode;
	}

	public void setAreaShopCode(String areaShopCode) {
		this.areaShopCode = areaShopCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "OrganizationVo [id=" + id + ", name=" + name + ", pName="
				+ pName + ", pid=" + pid + "]";
	}

}
