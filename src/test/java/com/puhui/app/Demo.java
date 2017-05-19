package com.puhui.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.po.AppCustomer;
import com.puhui.app.service.SwaggerService;
import com.puhui.cc.cloud.api.vo.LendLossDataVo;
import com.puhui.cc.cloud.api.vo.ResultVo;
import com.puhui.uc.vo.RemoteStaffVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by yhl on 2016/11/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    @Autowired
    private AppCustomerDao appCustomerDao;

       @Autowired
    private SwaggerService swaggerService;

    // xyb73d4153d83ceffe351fc3acaf9a1ec020160926 --- 15512345674
    //xy75806fac5062f0ac6cfa4f1bc77251601eec07d6a4cddcd57dba00744f1f593220160926 -- 110226198005100059
    @Test
    public void appCustomer(){
        List<Map<String, Object>> list =  appCustomerDao.getIdNoMethod("110226198005100059");
        logger.info("result: {}", JSONArray.toJSONString(list));
        AppCustomer appCustomer =  appCustomerDao.queryById(402l);
        logger.info("result: {}", JSONObject.toJSONString(appCustomer));
    }

    @Test
    public void ccReceiveLendLossData(){
        LendLossDataVo vo = new LendLossDataVo();
        vo.setCustomerName("张三");
        vo.setIdNo("500113197201155313");
        vo.setCity("RPA25301");
        vo.setMobile("15801010111");
        vo.setStore("RPA2530102");
        ResultVo resultVo = swaggerService.ccReceiveLendLossData(vo);
        logger.info("result: {}", JSONObject.toJSONString(resultVo));
    }

    @Test
    public void findStaffEmployeeNo(){
        String  no = "000011";
        RemoteStaffVo staffVo = swaggerService.employeeNo(no);
        logger.info(staffVo.getRealName());
    }

    @Test
    public void getIdNoMethod(){
        String idNo = "500113198004051998";
        List list = appCustomerDao.getIdNoMethod(idNo);
        logger.info("result : {}",list.size());
    }

}
