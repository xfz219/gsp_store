package com.puhui.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.po.AppCustomer;
import com.puhui.app.service.ChangeCustomerService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.service.impl.ChangeCustomerServiceImpl;
import com.puhui.cc.cloud.api.vo.LendLossDataVo;
import com.puhui.cc.cloud.api.vo.ResultVo;
import com.puhui.uc.vo.RemoteStaffVo;
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
 * Created by yhl on 2016/11/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerTest.class);

    @Autowired
    private ChangeCustomerServiceImpl changeCustomerService;

    @Test
    public void insertLog(){
        List<Long> ids = new ArrayList<>();
        ids.add(402L);
        ids.add(406L);
        Long staffId = 0l;
        changeCustomerService.insertLog(ids, staffId);
    }

}
