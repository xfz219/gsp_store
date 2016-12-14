package com.puhui.app.service.impl;

import com.puhui.app.common.page.mybatis.Page;
import com.puhui.app.dao.AppLendNoticeDao;
import com.puhui.app.po.AppLendNotice;
import com.puhui.app.po.AppLendNoticeVo;
import com.puhui.app.search.AppLendNoticeSearch;
import com.puhui.app.service.LendNoticeService;
import com.puhui.app.utils.BeanMapper;
import com.puhui.app.utils.CommonUtils;
import com.puhui.app.utils.LendNoticeStatus;
import com.puhui.app.vo.AppUserNoticeVo;
import com.puhui.app.vo.ReturnEntity;
import com.puhui.uc.api.service.RemoteOrganizationService;
import com.puhui.uc.api.service.RemoteStaffService;
import com.puhui.uc.api.service.RemoteUserCentreService;
import com.puhui.uc.vo.RemoteStaffVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LendNoticeServiceImpl implements LendNoticeService {
    private static final Logger logger = LoggerFactory.getLogger(LendNoticeServiceImpl.class);

    @Autowired
    private AppLendNoticeDao appLendNoticeDao;

    @Autowired
    private RemoteOrganizationService remoteOrganizationService;

    @Autowired
    private RemoteUserCentreService remoteUserCentreService;

    @Autowired
    private RemoteStaffService remoteStaffService;

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
                RemoteStaffVo createUser = remoteStaffService.queryByStaffId(createUserId);
                Long updateUserId = appLendNotice.getUpdateUser();
                if (updateUserId != null) {
                    RemoteStaffVo updateUser = remoteStaffService.queryByStaffId(updateUserId);
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
        if (flag.equals("add")) {
            appLendNotice.setCreateTime(new Date());
            appLendNotice.setNoticeStatus(LendNoticeStatus.CAO_GAO);
            appLendNotice.setCreateUser(loginStaff.getId());
            appLendNotice.setUpdateTime(new Date());
            appLendNotice.setAuthorName(loginStaff.getRealName());
            appLendNoticeDao.addLendNotice(appLendNotice);
        } else {
            appLendNotice.setUpdateUser(loginStaff.getId());
            appLendNotice.setUpdateTime(new Date());
        }
//        try {
//            appLendNoticeDao.saveOrUpdate(appLendNotice);
//        } catch (Exception e) {
//        	logger.error("add AppLendNotice error!" + e);
//        }
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
    	ReturnEntity returnEntity = new ReturnEntity(true, "");
    	try {
			AppLendNotice appLendNotice = appLendNoticeDao.queryById(id);
		    appLendNotice.setNoticeStatus(LendNoticeStatus.YI_FA_BU);
		    appLendNotice.setUpdateTime(new Date());
		    appLendNoticeDao.saveOrUpdate(appLendNotice);
		    AppUserNoticeVo userNoticeVo = new AppUserNoticeVo();
        	logger.info("开始推送id为:{}的系统公告", id);
            PropertyUtils.copyProperties(userNoticeVo, appLendNotice);
//            userDetailService.insertNoticeDetailMethod(userNoticeVo);
            logger.info("id为:{}的系统公告推送成功", id);
            returnEntity = new ReturnEntity(true, "发布系统公告成功");
        } catch (Exception e) {
        	logger.error("id为:{}的系统公告推送异常", id, e);
        	throw new RuntimeException("系统公告推送异常");
        }
    	return returnEntity;
    }
    
}
