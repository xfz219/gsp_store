package com.puhui.app.po;

import com.puhui.uc.vo.RemoteStaffVo;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;
import java.util.Objects;

/**
 * @author dongchen
 */
public class LendNotice{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 公告标题
     */
    private String noticeTitle;
    /**
     * 公告状态
     */
    private String noticeStatus;
    /**
     * 公告部门
     */
    private String noticeDepartment;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private RemoteStaffVo createUser;
    /**
     * 更新人
     */
    private RemoteStaffVo updateUser;
    /**
     * 公告摘要
     */
    private String noticeAbstract;
    /**
     * 公告内容
     */
    private String noticeContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoticeAbstract() {
        return noticeAbstract;
    }

    public void setNoticeAbstract(String noticeAbstract) {
        this.noticeAbstract = noticeAbstract;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getNoticeDepartment() {
        return noticeDepartment;
    }

    public void setNoticeDepartment(String noticeDepartment) {
        this.noticeDepartment = noticeDepartment;
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

    public RemoteStaffVo getCreateUser() {
        return createUser;
    }

    public void setCreateUser(RemoteStaffVo createUser) {
        this.createUser = createUser;
    }

    public RemoteStaffVo getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(RemoteStaffVo updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LendNotice that = (LendNotice) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(noticeTitle, that.noticeTitle) &&
                Objects.equals(noticeStatus, that.noticeStatus) &&
                Objects.equals(noticeDepartment, that.noticeDepartment) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(updateUser, that.updateUser) &&
                Objects.equals(noticeAbstract, that.noticeAbstract) &&
                Objects.equals(noticeContent, that.noticeContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, noticeTitle, noticeStatus, noticeDepartment, createTime, updateTime, createUser, updateUser, noticeAbstract, noticeContent);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
