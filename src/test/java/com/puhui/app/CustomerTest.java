package com.puhui.app;

import com.puhui.app.dao.AppVersionDao;
import com.puhui.app.po.AppPrizesSecret;
import com.puhui.app.service.PrizesService;
import com.puhui.app.service.impl.ChangeCustomerServiceImpl;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试类
 * Created by yhl on 2016/11/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerTest.class);

    @Autowired
    private ChangeCustomerServiceImpl changeCustomerService;

    @Autowired
    private AppVersionDao appVersionDao;

    @Autowired
    private PrizesService prizesService;
    @Test
    public void findList(){
        List<Map<String,Object>> list = appVersionDao.findList();
        logger.info("result: {}", JSONArray.fromObject(list).toString());
    }

    @Test
    public void insertLog(){
        List<Long> ids = new ArrayList<>();
        ids.add(402L);
        ids.add(406L);
        Long staffId = 0l;
        changeCustomerService.insertLog(ids, staffId);
    }

    @Test
    public void findList1(){
        AppPrizesSecret aps = new AppPrizesSecret();
        List<AppPrizesSecret> findList = prizesService.findList(aps);
        logger.info("result: {}", JSONArray.fromObject(findList).toString());
    }

}
