package com.puhui.app.service.impl;

import com.puhui.app.dao.AppLendShopDao;
import com.puhui.app.dao.AppPrizesNumberDao;
import com.puhui.app.dao.AppPrizesSecretDao;
import com.puhui.app.enums.PrizeChannel;
import com.puhui.app.enums.PrizeType;
import com.puhui.app.po.AppLendShop;
import com.puhui.app.po.AppPrizesNumber;
import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.service.AppLendShopService;
import com.puhui.app.service.PrizesService;
import com.puhui.app.utils.SensitiveInfoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AppLendShopServiceImpl implements AppLendShopService {

	@Autowired
    private AppLendShopDao appLendShopDao;

    @Override
    public List<AppLendShop> findList(AppLendShop als) {
        List<AppLendShop> alsList = appLendShopDao.findList(als.getShopName(), als.getEnabled());
        if (!alsList.isEmpty()) {
            return alsList;
        }
        return new ArrayList<>();
    }

    @Override
    public void updateAppLendShop(AppLendShop als) {
        appLendShopDao.updateAppLendShop(als);
    }

    @Override
    public void addAppLendShop(AppLendShop als) {
        appLendShopDao.addAppLendShop(als);
    }

    @Override
    public void updateEnabledById(long id, boolean enabled) {
        appLendShopDao.updateEnabledById(id, enabled);
    }

}
