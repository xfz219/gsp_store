package com.puhui.app.listener;


import com.alibaba.fastjson.JSONObject;
import com.puhui.app.service.CustomerCluesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;




public class CcLendSalesListener implements MessageListener{
	
    private static final Logger logger = LoggerFactory.getLogger(CcLendSalesListener.class);

    @Autowired
    private CustomerCluesService customerCluesService;
	
    @Override
    public void onMessage(Message message) {
    	logger.info("接收cc系统推送进件开始---------");
    	try {
    		 if (message instanceof TextMessage) {
        		 TextMessage om = (TextMessage) message;
        		logger.info("接收cc系统推送进件数据为:{}",om.getText());
        		JSONObject jsonObj = JSONObject.parseObject(om.getText());
        		customerCluesService.insertAppUserToPromote(jsonObj);
        	 }
		} catch (Exception e) {
			logger.error("接收cc系统推送进件异常,异常原因:{}---------",e);
		}
    	
    }
}
