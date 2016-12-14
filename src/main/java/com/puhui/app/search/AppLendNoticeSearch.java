package com.puhui.app.search;

import java.util.Date;

/**
 * Created by zhouwentong on 2016/12/14.
 * 公告搜索
 */
public class AppLendNoticeSearch extends BaseSearch{

    private String authorName;
    private String noticeStatus;
    private Date startDate;
    private Date endDate;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
