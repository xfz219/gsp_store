package com.puhui.app.po;

import com.puhui.uc.vo.RemoteStaffVo;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * Created by zhouwentong on 2016/12/13.
 * 对应的页面 Vo
 */
public class AppLendNoticeVo{
    private static final long serialVersionUID = 14324234L;

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
    private RemoteStaffVo createUserVo;
    /**
     * 更新人
     */
    private RemoteStaffVo updateUserVo;
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

    public String getNoticeTitle() {
        return noticeTitle;
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

    public RemoteStaffVo getCreateUserVo() {
        return createUserVo;
    }

    public void setCreateUserVo(RemoteStaffVo createUserVo) {
        this.createUserVo = createUserVo;
    }

    public RemoteStaffVo getUpdateUserVo() {
        return updateUserVo;
    }

    public void setUpdateUserVo(RemoteStaffVo updateUserVo) {
        this.updateUserVo = updateUserVo;
    }

    public String getNoticeAbstract() {
        return noticeAbstract;
    }

    public void setNoticeAbstract(String noticeAbstract) {
        this.noticeAbstract = noticeAbstract;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
