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
import java.util.Map;

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
            for (AppPrizesSecret ap : apsList) {
                ap.setCardNumber(ap.getCardNumber() != null ? SensitiveInfoUtils.sensitiveBankCard(ap.getCardNumber()) : "");
                ap.setPassword(SensitiveInfoUtils.sensitiveBankCard(ap.getPassword()));
            }
        }
        return apsList;
    }

    @Override
    public void addList(Map<String, Object> map) {

    }

}