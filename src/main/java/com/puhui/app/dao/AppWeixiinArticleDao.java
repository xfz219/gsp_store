package com.puhui.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.puhui.app.po.AppLendNotice;
import com.puhui.app.po.AppWeixiinArticle;
import com.puhui.app.search.AppLendNoticeSearch;

@Repository
public interface AppWeixiinArticleDao {

    List<AppWeixiinArticle> qryNoticeList(AppLendNoticeSearch vo);

    long qryNoticeCount(AppLendNotice vo);

    void saveOrUpdate(AppLendNotice appLendNotice);

    void delete(@Param("id") Long id);

    AppWeixiinArticle queryById(Long id);

    void addLendNotice(AppWeixiinArticle appWeixiinArticle);

    void updateNotice(AppWeixiinArticle appWeixiinArticle);
}
