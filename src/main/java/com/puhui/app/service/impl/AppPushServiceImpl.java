package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.dao.AppCustomerMessageDao;
import com.puhui.app.dao.AppCustomerTokenDao;
import com.puhui.app.dao.AppUserMessageDao;
import com.puhui.app.dao.AppUserTokenDao;
import com.puhui.app.po.AppCustomerToken;
import com.puhui.app.po.AppUserMessage;
import com.puhui.app.po.AppUserToken;
import com.puhui.app.service.AppPushService;
import com.puhui.app.utils.PushUtil;
import com.puhui.app.vo.AppPushMessageVo;

@Service
public class AppPushServiceImpl implements AppPushService {
	private static final Logger logger = LoggerFactory.getLogger(AppPushServiceImpl.class);
	@Autowired
	private AppCustomerDao appCustomerDao;
	@Autowired
	private AppCustomerTokenDao appCustomerTokenDao;
	@Autowired
	private AppUserTokenDao appUserTokenDao;
	@Autowired
	private AppCustomerMessageDao appCustomerMessageDao;
	@Autowired
	private AppUserMessageDao appUserMessageDao;

	@Override
	public void pushMessageUser(AppPushMessageVo appPushMessageVo) {
			// 向销售经理消息表添加加消息
			// 向消息表插入消息
			AppUserMessage appUserMessage = new AppUserMessage();
			appUserMessage.setSellerNumber(null);// 员工编号
			appUserMessage.setAppLendRequestId(appPushMessageVo.getAppLendRequestId());// 进件id
			appUserMessage.setContext(null);// 消息内容
			appUserMessage.setIsRead(2);// (2：未读；1：已读；)
			appUserMessage.setMessageType(appPushMessageVo.getType());// 消息类型
			appUserMessage.setSendStatus(1);// 是否已经推送(0：未推送；1：已推送；)
			appUserMessageDao.insertUserMessage(appUserMessage);
			// 查询设备
			AppUserToken appUserToken = new AppUserToken();
			List<AppUserToken> list = appUserTokenDao.getAppUserToken(appUserToken);
			appPushMessageVo.setSellerNumber(appUserMessage.getSellerNumber());
			new Thread(new Runner(appPushMessageVo, String.valueOf(appPushMessageVo.getPushModel()), "name", "mobile",
					appUserMessage.getId(), list, null, "toUser")).start();
	}
	
	@Override
	public void pushMessageCustomer(AppPushMessageVo appPushMessageVo) {
		List<AppCustomerToken> appCustomerTokenList = new ArrayList<>();
		String pushType = appPushMessageVo.getPushType() == 1 ? "toUser" : "toCustomer";
		AppCustomerToken AppCustomerToken = new AppCustomerToken();
			// 查询设备
			List<AppCustomerToken> list = appCustomerTokenDao.getAppCustomerToken(AppCustomerToken);
			for(int i = 0;i < list.size(); i++){
				appCustomerTokenList.add(list.get(i));
				if(appCustomerTokenList.size() > 4000 || i == list.size()-1){
					new Thread(new Runner(appPushMessageVo, appPushMessageVo.getOtherMessage(), "", "",
							1L, null, list, pushType)).start();
					appCustomerTokenList.clear();
				}
			}
	}



	private void push(AppPushMessageVo appPushMessageVo, String pushModel, String name, String mobile, Long mid,
			List<AppUserToken> listAppUserToken, List<AppCustomerToken> listAppCustomerToken, String pushType) {
		int IOS = 2;
		int Android = 3;//3是安卓
		int type = appPushMessageVo.getType();// 类型
		Map<String, String> pushModels = new HashMap<>();

		pushModels.put("t", String.valueOf(Calendar.getInstance().getTimeInMillis()));
		// 向销售经理终端推送消息
		List<String> aliasIOSList = new ArrayList<>();
		List<String> aliasAndroidList = new ArrayList<>();
		if (listAppUserToken != null) {
			for (AppUserToken token : listAppUserToken) {
				if (IOS == token.getClientType()) {
					aliasIOSList.add(token.getAlias());
				} else if (Android == token.getClientType()) {
						aliasAndroidList.add(token.getAlias());
				}
			}
			if (!aliasIOSList.isEmpty()) {
				pushModels.put("mid", String.valueOf(mid));
				pushModels.put("type", String.valueOf(type));
				PushUtil.sendMsg2IOS(aliasIOSList, pushModel, 1, pushModels, "IOS" + pushType);
				logger.info("消息推送成功!");
			}
		}
		if (listAppCustomerToken != null) {
			for (AppCustomerToken token : listAppCustomerToken) {
				if (IOS == token.getClientType()) {
					aliasIOSList.add(token.getAlias());
				} else if (Android == token.getClientType() && null != token.getAlias()) {
					aliasAndroidList.add(token.getAlias());
				}
			}
		}
		if (!aliasAndroidList.isEmpty()) {
			pushModels.put("type", String.valueOf(type));
			pushModels.put("message", pushModel);
			pushModels.put("name", name);
			pushModels.put("mobile", mobile);
			pushModels.put("mid", mid + "");
			PushUtil.sendMsg2Android(aliasAndroidList, JSON.toJSONString(pushModels), null, "AND" + pushType);
			logger.info("消息推送成功");
		}
		
		if (!aliasIOSList.isEmpty()) {
			int badge = 0;
			pushModels.put("mid", mid + "");
			PushUtil.sendMsg2IOS(aliasIOSList, pushModel, badge, pushModels, "IOS" + pushType);
			logger.info("消息推送成功");
		}
	}

	class Runner implements Runnable {

		private final AppPushMessageVo appPushMessageVo;

		private final String pushModel;

		private final String name;

		private final String mobile;

		private final Long mid;

		private final List<AppUserToken> listAppUserToken;

		private final List<AppCustomerToken> listAppCustomerToken;

		private final String pushType;

		public Runner(AppPushMessageVo appPushMessageVo, String pushModel, String name, String mobile, Long mid,
				List<AppUserToken> listAppUserToken, List<AppCustomerToken> listAppCustomerToken, String pushType) {
			this.appPushMessageVo = appPushMessageVo;
			this.pushModel = pushModel;
			this.name = name;
			this.mobile = mobile;
			this.mid = mid;
			this.listAppUserToken = listAppUserToken;
			this.listAppCustomerToken = listAppCustomerToken;
			this.pushType = pushType;
		}

		@Override
		public void run() {
			push(appPushMessageVo, pushModel, name, mobile, mid, listAppUserToken, listAppCustomerToken, pushType);
		}

	}

}
