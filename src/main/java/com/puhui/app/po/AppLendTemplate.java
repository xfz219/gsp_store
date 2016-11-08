package com.puhui.app.po;

import java.util.Date;

/**
 * 模板
 */
public class AppLendTemplate extends BaseBean {
    private static final long serialVersionUID = 150702645541396425L;
    

    /**
     * 模板类型
     */
    private String templetType;

    /**
     * 模板描述
     */
    private String templetDesc;

    /**
     * 模板内容
     */
    private String templetContent;

    /**
     * 模板标题
     */
    private String templetTitle;

    /**
     * 创建时间
     */
    private Date createTime;

	public String getTempletType() {
		return templetType;
	}

	public void setTempletType(String templetType) {
		this.templetType = templetType;
	}

	public String getTempletDesc() {
		return templetDesc;
	}

	public void setTempletDesc(String templetDesc) {
		this.templetDesc = templetDesc;
	}

	public String getTempletContent() {
		return templetContent;
	}

	public void setTempletContent(String templetContent) {
		this.templetContent = templetContent;
	}

	public String getTempletTitle() {
		return templetTitle;
	}

	public void setTempletTitle(String templetTitle) {
		this.templetTitle = templetTitle;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
