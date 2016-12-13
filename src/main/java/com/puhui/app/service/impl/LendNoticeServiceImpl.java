package com.puhui.app.service.impl;

import com.puhui.app.api.UserDetailService;
import com.puhui.app.dao.LendNoticeDao;
import com.puhui.app.po.LendNotice;
import com.puhui.app.service.LendNoticeService;
import com.puhui.app.utils.CommonUtils;
import com.puhui.app.utils.LendNoticeStatus;
import com.puhui.app.vo.AppUserNoticeVo;
import com.puhui.app.vo.ReturnEntity;
import com.puhui.uc.vo.RemoteStaffVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LendNoticeServiceImpl implements LendNoticeService {
    private static final Logger logger = LoggerFactory.getLogger(LendNoticeServiceImpl.class);

    @Autowired
    private LendNoticeDao lendNoticeDao;

    @Autowired
    private UserDetailService userDetailService;

    /**
     * 获取dagraid组装数据
     */
    @Override
    public Map<String, Object> qryLendNoticeList(LendNotice vo) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<LendNotice> list = null;
        long count = 0;
        try {
            list = lendNoticeDao.qryNotcieList(vo);
            count = lendNoticeDao.qryNotciecount(vo);
        } catch (Exception e) {
            logger.error("query lendNotice error!" + e);
        }
        map.put("total", count);
        map.put("rows", list);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void updateOrSaveLendNotice(LendNotice lendNotice, String flag) {
        Subject currStaff = SecurityUtils.getSubject();
        RemoteStaffVo loginStaff = CommonUtils.getLoginStaff();
        if (flag.equals("add")) {
            lendNotice.setCreateTime(new Date());
            lendNotice.setNoticeStatus(LendNoticeStatus.CAO_GAO);
        } else {
            lendNotice.setUpdateUser(loginStaff);
        }
        lendNotice.setCreateUser(loginStaff);
        lendNotice.setUpdateTime(new Date());
        try {
            lendNoticeDao.saveOrUpdate(lendNotice);
        } catch (Exception e) {
        	logger.error("add LendNotice error!" + e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReturnEntity deleteLendNotice(Long id) {
    	ReturnEntity returnEntity = new ReturnEntity(true, "");
    	try {
			lendNoticeDao.delete(id);
			logger.info("删除id为：{}的系统公告成功", id);
			returnEntity = new ReturnEntity(true, "删除系统公告成功");
		} catch (Exception e) {
			logger.error("删除id为：{}的系统公告失败", id, e);
			returnEntity = new ReturnEntity(false, "删除系统公告失败");
		}
        return returnEntity;
    }

    @Override
    public LendNotice getLendNoticeById(Long id) {
        return lendNoticeDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReturnEntity isuseLendNotice(Long id) {
    	ReturnEntity returnEntity = new ReturnEntity(true, "");
    	try {
			LendNotice lendNotice = lendNoticeDao.queryById(id);
		    lendNotice.setNoticeStatus(LendNoticeStatus.YI_FA_BU);
		    lendNotice.setUpdateTime(new Date());
		    lendNoticeDao.saveOrUpdate(lendNotice);
		    AppUserNoticeVo userNoticeVo = new AppUserNoticeVo();
        	logger.info("开始推送id为:{}的系统公告", id);
            PropertyUtils.copyProperties(userNoticeVo, lendNotice);
            userDetailService.insertNoticeDetailMethod(userNoticeVo);
            logger.info("id为:{}的系统公告推送成功", id);
            returnEntity = new ReturnEntity(true, "发布系统公告成功");
        } catch (Exception e) {
        	logger.error("id为:{}的系统公告推送异常", id, e);
        	throw new RuntimeException("系统公告推送异常");
        }
    	return returnEntity;
    }
    
}
