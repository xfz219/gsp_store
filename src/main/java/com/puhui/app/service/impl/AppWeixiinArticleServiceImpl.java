package com.puhui.app.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.dao.AppWeixinArticleDao;
import com.puhui.app.po.AppLendNotice;
import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.service.AppPushService;
import com.puhui.app.service.AppWeixiinArticleService;
import com.puhui.app.utils.LendNoticeStatus;
import com.puhui.app.vo.AppPushMessageVo;
import com.puhui.app.vo.ReturnEntity;

@Service
public class AppWeixiinArticleServiceImpl implements AppWeixiinArticleService {
    private static final Logger logger = LoggerFactory.getLogger(AppWeixiinArticleServiceImpl.class);

    @Autowired
    private AppWeixinArticleDao appWeixiinArticleDao;

    @Autowired
    private AppPushService appPushService;

    /**
     * 获取dagraid组装数据
     * @param appLendNoticeSearch
     */
    @Override
    public Map<String, Object> qryLendNoticeList(AppLendNoticeSearch appLendNoticeSearch) {
        Map<String, Object> map = new HashMap<>();
        List<AppWeixinArticle> list = null;
        Page page = Page.getPage(appLendNoticeSearch.getPage(),appLendNoticeSearch.getRows());
        try {
            list = appWeixiinArticleDao.qryNoticeList(appLendNoticeSearch);
        } catch (Exception e) {
            logger.error("query appLendNotice error!" + e);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            map.put("total", page.getTotalCount());
            map.put("rows", list);
        } else {
            map.put("total", 0);
            map.put("rows", Collections.emptyList());
        }
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void updateOrSaveLendNotice(AppWeixinArticle appWeixiinArticle, String flag) {
        if (Objects.equals(flag, "add")) {
        	appWeixiinArticle.setArticleStatus(LendNoticeStatus.CAO_GAO);
            appWeixiinArticleDao.addLendNotice(appWeixiinArticle);
        } else {
            appWeixiinArticleDao.updateNotice(appWeixiinArticle);

        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReturnEntity deleteLendNotice(Long id) {
    	ReturnEntity returnEntity ;
    	try {
    		appWeixiinArticleDao.delete(id);
			logger.info("删除id为：{}的系统公告成功", id);
			returnEntity = new ReturnEntity(true, "删除微信文章成功");
		} catch (Exception e) {
			logger.error("删除id为：{}的系统公告失败", id, e);
			returnEntity = new ReturnEntity(false, "删除微信文章失败");
		}
        return returnEntity;
    }

    @Override
    public AppWeixinArticle getLendNoticeById(Long id) {
        return appWeixiinArticleDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReturnEntity isuseLendNotice(Long id) {
    	ReturnEntity returnEntity;
    	try {
    		AppWeixinArticle appWeixiinArticle = appWeixiinArticleDao.queryById(id);
    		appWeixiinArticle.setArticleStatus(LendNoticeStatus.YI_FA_BU);
		    appWeixiinArticleDao.updateNotice(appWeixiinArticle);
            returnEntity = new ReturnEntity(true, "发布系统公告成功");
        } catch (Exception e) {
        	logger.error("id为:{}的微信文章异常", id, e);
        	throw new IllegalStateException("系统公告推送异常");
        }
    	return returnEntity;
    }

    private AppPushMessageVo generatePushMessageVo(AppLendNotice noticeVo) {
        AppPushMessageVo appPushMessageVoToUser = new AppPushMessageVo();// 推送给销售经理
        appPushMessageVoToUser.setAppLendRequestId(noticeVo.getId());// 进件id
        appPushMessageVoToUser.setPushType(1);// 推送类型
        appPushMessageVoToUser.setType(1000);// 公告标识 1000
        appPushMessageVoToUser.setMessage(noticeVo.getNoticeTitle());// 标题
        appPushMessageVoToUser.setOtherMessage(noticeVo.getNoticeDepartment());//大区
        return appPushMessageVoToUser;
    }
    
}
