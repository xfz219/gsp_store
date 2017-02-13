package com.puhui.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.dao.AppWeixinArticleDao;
import com.puhui.app.po.AppWeixinArticle;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.service.AppWeixinArticleService;
import com.puhui.app.utils.FileUtil;
import com.puhui.app.utils.LendNoticeStatus;
import com.puhui.app.vo.ReturnEntity;

@Service
public class AppWeixinArticleServiceImpl implements AppWeixinArticleService {
    private static final Logger logger = LoggerFactory.getLogger(AppWeixinArticleServiceImpl.class);
	public final static String ATTACHMENT_PATH = new StringBuffer().append(File.separator).append("upload").append(File.separator).append("puhui-lend").append(File.separator).append("board")
			.append(File.separator).append("pic").append(File.separator).toString();

    @Autowired
    private AppWeixinArticleDao appWeixiinArticleDao;

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
    	AppWeixinArticle lendAd = this.getLendNoticeById(appWeixiinArticle.getId());
    	appWeixiinArticle.setArticleStatus(LendNoticeStatus.CAO_GAO);
    	appWeixiinArticle.setOriginalPicName(lendAd.getOriginalPicName());
    	appWeixiinArticle.setPicAccessUrl(lendAd.getPicAccessUrl());
    	appWeixiinArticle.setPicAddressUrl(lendAd.getPicAddressUrl());
        appWeixiinArticleDao.updateNotice(appWeixiinArticle);
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
    
    public Long saveLendAdvertisementPic(AppWeixinArticle lendAd, MultipartFile myfile, HttpServletRequest request){
        String savePath = null;
        Long id = lendAd.getId();
		try {
			savePath = this.getPath(lendAd, myfile, request);
		} catch (Exception e) {
			logger.error("广告位保存异常：",e);
		} 
        lendAd.setPicAddressUrl(savePath);
        String repStr = new StringBuffer().append(File.separator).append("upload").append(File.separator).append("puhui-lend").toString();
        String picAccessUrl = savePath.replace(repStr, "");
        lendAd.setPicAccessUrl(picAccessUrl);
  		if(null != id && id > 0){
  			appWeixiinArticleDao.updateNotice(lendAd);
  		}else{
  			//添加
  			appWeixiinArticleDao.addLendNotice(lendAd);
  		}
        return lendAd.getId();
	}
	
	/**
     * @throws IOException
     * @throws IllegalStateException
     * @Title: getPath
     * @Description: 获取广告位图片上传路径
     * @param @param lendAdvertisement
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    private String getPath(AppWeixinArticle lendAd, MultipartFile myfile, HttpServletRequest request) throws IllegalStateException,
            IOException {
        String filePath = new StringBuffer(60).append(ATTACHMENT_PATH)
                .append(DateFormatUtils.format(new Date(), "yyyy-MM-dd")).append(File.separator)
                .append(DateFormatUtils.format(new Date(), "HH-mm-ss")).toString();
        if (!FileUtil.isExists(filePath)) {
            FileUtil.mkdirs(filePath);
        }
        String fileExtention = FileUtil.getExtension(lendAd.getOriginalPicName());
        String uuid = UUID.randomUUID().toString();
        File file = new File(filePath, new StringBuffer(30).append(uuid).append(".").append(fileExtention).toString());
        myfile.transferTo(file);
        String basePath = new StringBuffer(180).append(request.getScheme()).append(":").append(File.separator).append(File.separator).append(request.getServerName()).append(":").
        		append(request.getServerPort()).append(filePath).append(File.separator).append(uuid).append(".").append(fileExtention).toString();
        return basePath;
    }    
    
    
}
