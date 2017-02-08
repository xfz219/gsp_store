package com.puhui.app.po;

import java.util.Date;

/**
 * @author lichunyue
 */
public class AppWeixinArticle {

    private static final long serialVersionUID = 143242334L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 文章标题
     */
    private String article;
    /**
     * 作者姓名
     */
    private String authorName;
    /**
     * 文章内容
     */
    private String notice;
    /**
     * 简介
     */
    private String synopsis;
    /**
     * 文件原名
     */
    private String originalPicName;
    /**
     * 图片访问url
     */
    private String picAccessUrl;
    /**
     * 图片地址url
     */
    private String picAddressUrl;
    /**
     * 公告状态(0未发布,1发布)
     */
    private String articleStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    
    
	public String getOriginalPicName() {
		return originalPicName;
	}
	public void setOriginalPicName(String originalPicName) {
		this.originalPicName = originalPicName;
	}
	public String getPicAccessUrl() {
		return picAccessUrl;
	}
	public void setPicAccessUrl(String picAccessUrl) {
		this.picAccessUrl = picAccessUrl;
	}
	public String getPicAddressUrl() {
		return picAddressUrl;
	}
	public void setPicAddressUrl(String picAddressUrl) {
		this.picAddressUrl = picAddressUrl;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getArticleStatus() {
		return articleStatus;
	}
	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
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

    
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AppWeixiinArticle that = (AppWeixiinArticle) o;
//        return Objects.equals(id, that.id) &&
//                Objects.equals(noticeTitle, that.noticeTitle) &&
//                Objects.equals(noticeStatus, that.noticeStatus) &&
//                Objects.equals(noticeDepartment, that.noticeDepartment) &&
//                Objects.equals(createTime, that.createTime) &&
//                Objects.equals(updateTime, that.updateTime) &&
//                Objects.equals(createUser, that.createUser) &&
//                Objects.equals(updateUser, that.updateUser) &&
//                Objects.equals(noticeAbstract, that.noticeAbstract) &&
//                Objects.equals(noticeContent, that.noticeContent);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, noticeTitle, noticeStatus, noticeDepartment, createTime, updateTime, createUser, updateUser, noticeAbstract, noticeContent);
//    }
//
//    @Override
//    public String toString() {
//        return ReflectionToStringBuilder.toString(this);
//    }
}
