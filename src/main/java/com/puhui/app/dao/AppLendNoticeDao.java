package com.puhui.app.dao;

import com.puhui.app.po.AppLendNotice;
import com.puhui.app.search.AppLendNoticeSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppLendNoticeDao {

    List<AppLendNotice> qryNoticeList(AppLendNoticeSearch vo);

    long qryNoticeCount(AppLendNotice vo);

    void saveOrUpdate(AppLendNotice appLendNotice);

    void delete(@Param("id") Long id);

    AppLendNotice queryById(Long id);

    void addLendNotice(AppLendNotice appLendNotice);
}
