package com.puhui.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.puhui.app.po.AppLendNotice;
import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.search.AppLendNoticeSearch;

@Repository
public interface AppWeixinArticleDao {

    List<AppWeixinArticle> qryNoticeList(AppLendNoticeSearch vo);

    long qryNoticeCount(AppLendNotice vo);

    void saveOrUpdate(AppLendNotice appLendNotice);

    void delete(@Param("id") Long id);

    AppWeixinArticle queryById(Long id);

    void addLendNotice(AppWeixinArticle appWeixiinArticle);

    void updateNotice(AppWeixinArticle appWeixiinArticle);
}