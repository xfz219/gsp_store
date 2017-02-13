package com.puhui.app.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.vo.ReturnEntity;

public interface AppWeixinArticleService {

    Map<String, Object> qryLendNoticeList(AppLendNoticeSearch appLendNoticeSearch);

    void updateOrSaveLendNotice(AppWeixinArticle appWeixiinArticle, String flag);

    ReturnEntity deleteLendNotice(Long id);

    AppWeixinArticle getLendNoticeById(Long id);

    ReturnEntity isuseLendNotice(Long id);
    
    Long saveLendAdvertisementPic(AppWeixinArticle lendAd, MultipartFile myfile, HttpServletRequest request);
}
