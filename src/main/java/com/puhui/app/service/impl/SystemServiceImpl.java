package com.puhui.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.puhui.app.dao.*;
import com.puhui.app.po.*;
import com.puhui.app.service.AppPushService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.service.SystemService;
import com.puhui.app.utils.BasisUtils;
import com.puhui.app.utils.PushUtil;
import com.puhui.app.vo.AppPushMessageVo;
import com.puhui.uc.vo.RemoteStaffVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SystemServiceImpl implements SystemService {
	private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

	@Autowired
    private AppVersionDao appVersionDao;

    @Override
    public List<Map<String, Object>> findList() {
        return appVersionDao.findList();
    }

    @Override
    public boolean update(Map<String, Object> params) {
        Object typeName = params.get("typeName");
        if(typeName == null){
            return false;
        }
        int cnt = 0;
        if("门店版".equals(typeName.toString())){
            cnt = appVersionDao.updateUser(params);
        } else if ("个人版".equals(typeName.toString())) {
            cnt = appVersionDao.updateCustomer(params);
        }
        return (cnt==1);
    }

}
