package com.puhui.app.search;

/**
 * Created by zhouwentong on 2016/12/14.
 */
public class BaseSearch {
    private int page = 0;

    private int rows = 20;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
