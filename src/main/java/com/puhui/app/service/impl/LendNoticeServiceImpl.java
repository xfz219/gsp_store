package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.dao.AppLendNoticeDao;
import com.puhui.app.po.AppLendNotice;
import com.puhui.app.po.AppLendNoticeVo;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.service.AppPushService;
import com.puhui.app.service.LendNoticeService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.utils.BeanMapper;
import com.puhui.app.utils.CommonUtils;
import com.puhui.app.utils.LendNoticeStatus;
import com.puhui.app.vo.AppPushMessageVo;
import com.puhui.app.vo.AppUserNoticeVo;
import com.puhui.app.vo.ReturnEntity;
import com.puhui.uc.vo.RemoteStaffVo;

@Service
public class LendNoticeServiceImpl implements LendNoticeService {
    private static final Logger logger = LoggerFactory.getLogger(LendNoticeServiceImpl.class);

    @Autowired
    private AppLendNoticeDao appLendNoticeDao;

    @Autowired
    private SwaggerService swaggerService;

    @Autowired
    private AppPushService appPushService;

    /**
     * 获取dagraid组装数据
     * @param appLendNoticeSearch
     */
    @Override
    public Map<String, Object> qryLendNoticeList(AppLendNoticeSearch appLendNoticeSearch) {
        Map<String, Object> map = new HashMap<>();
        List<AppLendNotice> list = null;
        Page page = Page.getPage(appLendNoticeSearch.getPage(),appLendNoticeSearch.getRows());
        try {
            list = appLendNoticeDao.qryNoticeList(appLendNoticeSearch);
        } catch (Exception e) {
            logger.error("query appLendNotice error!" + e);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            List<AppLendNoticeVo> list1 = new ArrayList<>(list.size());
            list.forEach(appLendNotice -> {
                AppLendNoticeVo map1 = BeanMapper.map(appLendNotice, AppLendNoticeVo.class);
                Long createUserId = appLendNotice.getCreateUser();
                RemoteStaffVo createUser = swaggerService.ucId(createUserId);
                Long updateUserId = appLendNotice.getUpdateUser();
                if (updateUserId != null) {
                    RemoteStaffVo updateUser = swaggerService.ucId(updateUserId);
                    map1.setUpdateUserVo(updateUser);
                }
                map1.setCreateUserVo(createUser);
                list1.add(map1);
            });
            map.put("total", page.getTotalCount());
            map.put("rows", list1);
        } else {
            map.put("total", 0);
            map.put("rows", Collections.emptyList());
        }
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void updateOrSaveLendNotice(AppLendNotice appLendNotice, String flag) {
        RemoteStaffVo loginStaff = CommonUtils.getLoginStaff();
        if (Objects.equals(flag, "add")) {
            appLendNotice.setCreateTime(new Date());
            appLendNotice.setNoticeStatus(LendNoticeStatus.CAO_GAO);
            appLendNotice.setCreateUser(loginStaff.getId());
            appLendNotice.setUpdateTime(new Date());
            appLendNotice.setAuthorName(loginStaff.getRealName());
            appLendNoticeDao.addLendNotice(appLendNotice);
        } else {
            appLendNotice.setUpdateUser(loginStaff.getId());
            appLendNotice.setUpdateTime(new Date());
            appLendNoticeDao.updateNotice(appLendNotice);

        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReturnEntity deleteLendNotice(Long id) {
    	ReturnEntity returnEntity ;
    	try {
			appLendNoticeDao.delete(id);
			logger.info("删除id为：{}的系统公告成功", id);
			returnEntity = new ReturnEntity(true, "删除系统公告成功");
		} catch (Exception e) {
			logger.error("删除id为：{}的系统公告失败", id, e);
			returnEntity = new ReturnEntity(false, "删除系统公告失败");
		}
        return returnEntity;
    }

    @Override
    public AppLendNotice getLendNoticeById(Long id) {
        return appLendNoticeDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReturnEntity isuseLendNotice(Long id) {
    	ReturnEntity returnEntity;
    	try {
			AppLendNotice appLendNotice = appLendNoticeDao.queryById(id);
		    appLendNotice.setNoticeStatus(LendNoticeStatus.YI_FA_BU);
		    appLendNotice.setUpdateTime(new Date());
		    appLendNoticeDao.updateNotice(appLendNotice);
		    AppUserNoticeVo userNoticeVo = new AppUserNoticeVo();
        	logger.info("开始推送id为:{}的系统公告", id);
            PropertyUtils.copyProperties(userNoticeVo, appLendNotice);
            logger.info("id为:{}的系统公告推送成功", id);
            AppPushMessageVo appPushMessageVo = generatePushMessageVo(appLendNotice);
            appPushService.pushNotice(appPushMessageVo);
            returnEntity = new ReturnEntity(true, "发布系统公告成功");
        } catch (Exception e) {
        	logger.error("id为:{}的系统公告推送异常", id, e);
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
