package com.puhui.app.service.impl;

import com.puhui.app.dao.AppPrizesSecretDao;
import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.service.PrizesService;
import com.puhui.app.utils.SensitiveInfoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizesServiceImpl implements PrizesService {
	private static final Logger logger = LoggerFactory.getLogger(PrizesServiceImpl.class);

	@Autowired
    private AppPrizesSecretDao appPrizesSecretDao;

    @Override
    public List<AppPrizesSecret> findList(AppPrizesSecret aps) {
        String prizeType = aps.getPrizeType() != null ? aps.getPrizeType().name() : "";
        List<AppPrizesSecret> apsList = appPrizesSecretDao.findList(prizeType, aps.getUse());
        if (!apsList.isEmpty()) {
            for (AppPrizesSecret appPrizesSecret : apsList) {
                appPrizesSecret.setCardNumber(SensitiveInfoUtils.sensitiveBankCard(appPrizesSecret.getCardNumber()));
                appPrizesSecret.setPassword(SensitiveInfoUtils.sensitiveBankCard(appPrizesSecret.getPassword()));
            }
        }
        return apsList;
    }

}
