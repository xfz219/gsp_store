package com.puhui.app.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.puhui.app.po.AppCustomerToken;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

/**
 * App端消息推送
 * @author liuhongyu
 *
 */
public class PushUtil extends Thread{
	private static Logger logger = LoggerFactory.getLogger(PushUtil.class);
	
	//android
	private static String ANDROID_APP_KEY_USER = PropertiesLoader.getProperty("push.androiduser.appkey");
	private static String ANDROID_MASTER_SECRET_USER = PropertiesLoader.getProperty("push.androiduseruser.mastersecret");
	private static String ANDROID_APP_KEY_CUSTOMER = PropertiesLoader.getProperty("push.androidcustomer.appkey");
	private static String ANDROID_MASTER_SECRET_CUSTOMER = PropertiesLoader.getProperty("push.androidcustomer.mastersecret");
	//ios
	private static boolean IOS_CERTIFICATE_ENVIRONMENT = Boolean.parseBoolean(PropertiesLoader.getProperty("push.ios.certificate.environment"));
	private static String IOS_CERTIFICATE_PATH_USER = PropertiesLoader.getProperty("push.iosuser.certificate.path");
	private static String IOS_CERTIFICATE_PATH_CUSTOMER = PropertiesLoader.getProperty("push.ioscustomer.certificate.path");
	private static String IOS_CERTIFICATE_PASSWORD = PropertiesLoader.getProperty("push.iosuser.certificate.password");
	
	private int clientType;
	private String clias;
	PushUtil(){}
	PushUtil(String clias,int clientType){
		this.clias = clias;
		this.clientType = clientType;
	}
	
	/**
	 * 向指定别名的IOS设备推送消息
	 * @param tokens 推送设备tokens列表
	 * @param alert 显示在ios通知的提醒信息
	 * @param badge 显示消息个数
	 * @param dictionarys 推送给设备的额外业务信息
	 */
	public static void sendMsg2IOS(List<String> tokens, String alert, int badge, Map<String, String> dictionarys,String pushtype){
		
		PushNotificationManager pushManager = null;
		try{
			
            List<Device> devices = new ArrayList<Device>();
            //封装divice 
            for (String token : tokens) {
                devices.add(new BasicDevice(token));
            }
            
            //封装 payLoad 对象
            PushNotificationPayload payLoad = new PushNotificationPayload(alert, badge, "default");

            for (Map.Entry<String, String> m : dictionarys.entrySet()) {  
            	payLoad.addCustomDictionary(m.getKey(), m.getValue());
            }
            
            // 2.获取连接
            pushManager = new PushNotificationManager();
            String file = PushUtil.class.getResource ("/").getFile(); 
            if(pushtype.equals("IOStoCustomer")){//推送给个人
            	pushManager.initializeConnection(new AppleNotificationServerBasicImpl(file+IOS_CERTIFICATE_PATH_CUSTOMER, IOS_CERTIFICATE_PASSWORD, IOS_CERTIFICATE_ENVIRONMENT));
            }else if(pushtype.equals("IOStoUser")){//推送给销售 
            	pushManager.initializeConnection(new AppleNotificationServerBasicImpl(file+IOS_CERTIFICATE_PATH_USER, IOS_CERTIFICATE_PASSWORD, IOS_CERTIFICATE_ENVIRONMENT));
            }
            
            // 3.推送消息
            PushedNotifications pushed = pushManager.sendNotifications(payLoad, devices);
            
            List<PushedNotification> success = PushedNotification.findSuccessfulNotifications(pushed);
            List<PushedNotification> failed = PushedNotification.findFailedNotifications(pushed);
         
            // 打印推送状态
            logger.info("推送[ios]消息.要推送总数={},成功={},失败={}", tokens.size(), success.size(), failed.size());
		}catch(Exception e){
			logger.error("推送[ios]消息出错.{}", e);
		}finally{
			try {
                if (pushManager != null) {
                    pushManager.stopConnection();
                }
            } catch (Exception e) {
                logger.error("推送[ios]消息,关闭PushNotificationManager对象出错.{}", e);
            }
		}
	}
	
	/**
	 * 向指定别名的android设备推送消息
	 * @param aliases 推送设备别名列表
	 * @param alert 显示在android通知的提醒信息
	 * @param extras 推送给设备的额外业务信息
	 */
	public static void sendMsg2Android(List<String> aliases, String alert, Map<String, String> extras,String pushtype){
		Builder b = PushPayload.newBuilder();//buildPushObject_ios_audienceMore_messageWithExtras().newBuilder();//PushPayload.newBuilder();
		//设置发送平台为android
		b.setPlatform(Platform.android());
		//判断是否群发
		if(aliases != null && !aliases.isEmpty()){
			logger.info("推送[android]消息到[{}]用户",aliases.size());
			b.setAudience(Audience.alias(aliases));
		}else{
			logger.info("推送[android]消息到[所有]用户");
			b.setAudience(Audience.all());
		}
		//设置发送通知体
		//b.setNotification(Notification.android(alert, TITLE, extras));
		//设置发送自定义消息
		b.setMessage(Message.content(alert));
		PushPayload payLoad = b.build();
		androidPush(payLoad,pushtype);
	}
	
	/**
	 * 向所有android设备推送通知
	 * @param alert 显示在android通知的提醒信息
	 * @param extras 推送给设备的额外业务信息
	 */
	public static void sendMsg2Android(String alert, Map<String, String> extras){
		sendMsg2Android(null, alert, extras,null);
	}
	
	/**
     * 向android设备发送消息
     * @param payload
     */
    private static void androidPush(PushPayload payload,String pushtype) {
        JPushClient jpushClient = null;
        if(pushtype.equals("ANDtoCustomer")){//推送给个人
        	logger.info("mastersecret={},appkey={}", ANDROID_MASTER_SECRET_CUSTOMER, ANDROID_APP_KEY_USER);
            jpushClient = new JPushClient(ANDROID_MASTER_SECRET_CUSTOMER, ANDROID_APP_KEY_CUSTOMER, 3);
        }else if(pushtype.equals("ANDtoUser")){//推送给销售
        	logger.info("mastersecret={},appkey={}", ANDROID_MASTER_SECRET_USER, ANDROID_MASTER_SECRET_CUSTOMER);
            jpushClient = new JPushClient(ANDROID_MASTER_SECRET_USER, ANDROID_APP_KEY_USER, 3);
        }
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("发送结果：" + result);
        } catch (APIConnectionException e) {
            logger.error("发送失败. 稍后再试. ", e);
        } catch (APIRequestException e) {
            logger.error("JPush服务端响应异常.", e);
            logger.info("HTTP状态: " + e.getStatus());
            logger.info("错误代码: " + e.getErrorCode());
            logger.info("错误消息: " + e.getErrorMessage());
        }
    }
    
    public static void push(AppCustomerToken appCT){
    	PushUtil p = new PushUtil(appCT.getAlias(),appCT.getClientType());
    	p.start();
		
    }
    
}
