package com.puhui.app.service.impl;

import com.puhui.app.dao.AppPrizesNumberDao;
import com.puhui.app.dao.AppPrizesSecretDao;
import com.puhui.app.enums.PrizeChannel;
import com.puhui.app.enums.PrizeType;
import com.puhui.app.po.AppPrizesNumber;
import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.service.PrizesService;
import com.puhui.app.utils.SensitiveInfoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PrizesServiceImpl implements PrizesService {
	private static final Logger logger = LoggerFactory.getLogger(PrizesServiceImpl.class);

	@Autowired
    private AppPrizesSecretDao appPrizesSecretDao;
	@Autowired
    private AppPrizesNumberDao appPrizesNumberDao;

    @Override
    public List<AppPrizesSecret> findList(AppPrizesSecret aps) {
        String prizeType = aps.getPrizeType() != null ? aps.getPrizeType().name() : "";
        List<AppPrizesSecret> apsList = appPrizesSecretDao.findList(prizeType, aps.getUse());
        if (!apsList.isEmpty()) {
            for (AppPrizesSecret ap : apsList) {
                ap.setCardNumber(ap.getCardNumber() != null ? SensitiveInfoUtils.sensitiveBankCard(ap.getCardNumber()) : "");
                ap.setPassword(SensitiveInfoUtils.sensitivePassword(ap.getPassword()));
            }
            return apsList;
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
        appPrizesNumberDao.addAppPrizesNumber(apn);

        appPrizesSecretDao.addAppPrizesSecret(apsList);

        m.put("result", "添加成功!");
        return m;
    }

}
