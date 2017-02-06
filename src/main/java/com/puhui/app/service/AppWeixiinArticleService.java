package com.puhui.app.service;

import java.util.Map;

import com.puhui.app.po.AppWeixiinArticle;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.vo.ReturnEntity;

public interface AppWeixiinArticleService {

    Map<String, Object> qryLendNoticeList(AppLendNoticeSearch appLendNoticeSearch);

    void updateOrSaveLendNotice(AppWeixiinArticle appWeixiinArticle, String flag);

    ReturnEntity deleteLendNotice(Long id);

    AppWeixiinArticle getLendNoticeById(Long id);

    ReturnEntity isuseLendNotice(Long id);
}
