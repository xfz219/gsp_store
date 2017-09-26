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
	private static final Logger logger = LoggerFactory.getLogger(AppLendShopServiceImpl.class);

	@Autowired
    private AppPrizesSecretDao appPrizesSecretDao;
	@Autowired
    private AppPrizesNumberDao appPrizesNumberDao;
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
    @Transactional(rollbackFor = Exception.class)
    public Map<String,String> addList(Map<String, String> map, List<Map<String, String>> secretList) throws Exception {
        List<AppPrizesSecret> apsList = new ArrayList<>();
        Map<String, String> m = new HashMap<>();
        PrizeChannel prizeChannel = PrizeChannel.valueOf(map.get("prizeChannel"));//奖励渠道
        PrizeType prizeType = PrizeType.valueOf(map.get("prizeType"));//奖励类型
        Integer prizeNumber = Integer.parseInt(map.get("prizeNumber"));//奖品数量

        for (Map<String, String> secretMap : secretList) {
            String cardNumber = secretMap.get("cardNumber");
            String password = secretMap.get("password");
            if (Objects.equals(password, "")) {
                continue;
            }
            AppPrizesSecret aps = new AppPrizesSecret();
            aps.setPrizeType(prizeType);
            aps.setCardNumber(cardNumber);
            aps.setPassword(password);
            apsList.add(aps);
        }

        if (prizeNumber != apsList.size()) {
            m.put("result", "奖品数量与附件不符!");
            return m;
        }

        AppPrizesNumber apn = new AppPrizesNumber();
        apn.setPrizeChannel(prizeChannel);
        apn.setPrizeType(prizeType);
        apn.setPrizeNumber(prizeNumber);
        int count = appPrizesNumberDao.getAppPrizesNumber(apn);
        if (count > 0) {
            appPrizesNumberDao.updateAppPrizesNumber(apn);
        } else {
            appPrizesNumberDao.addAppPrizesNumber(apn);

        }

        appPrizesSecretDao.addAppPrizesSecret(apsList);

        m.put("result", "添加成功!");
        return m;
    }

}
