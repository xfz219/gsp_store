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
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.dao.AppCustomerMessageDao;
import com.puhui.app.dao.AppCustomerTokenDao;
import com.puhui.app.dao.AppLendRequestDao;
import com.puhui.app.dao.AppLendTemplateDao;
import com.puhui.app.dao.AppUserMessageDao;
import com.puhui.app.dao.AppUserTokenDao;
import com.puhui.app.po.AppCustomer;
import com.puhui.app.po.AppCustomerMessage;
import com.puhui.app.po.AppCustomerToken;
import com.puhui.app.po.AppLendRequest;
import com.puhui.app.po.AppLendTemplate;
import com.puhui.app.po.AppUserMessage;
import com.puhui.app.po.AppUserToken;
import com.puhui.app.service.AppPushService;
import com.puhui.app.utils.BasisUtils;
import com.puhui.app.utils.PushUtil;
import com.puhui.app.vo.AppPushMessageVo;
import com.puhui.uc.api.service.RemoteLendAppUserCenterService;
import com.puhui.uc.vo.RemoteLendAppResultVo;

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
	private AppUserMessageDao appUserMessageDao;
	@Autowired
	private AppLendTemplateDao appLendTemplateDao;
	@Autowired
	private AppLendRequestDao appLendRequestDao;
	@Autowired
	private AppCustomerMessageDao appCustomerMessageDao;
	@Autowired
	private RemoteLendAppUserCenterService remoteLendAppUserCenterService;

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
		AppCustomerToken appCustomerToken = new AppCustomerToken();
			// 查询设备
			List<AppCustomerToken> list = appCustomerTokenDao.getAppCustomerToken(appCustomerToken);
			for(int i = 0;i < list.size(); i++){
				appCustomerTokenList.add(list.get(i));
				if(appCustomerTokenList.size() > 998 || i == list.size()-1){
					new Thread(new Runner(appPushMessageVo, appPushMessageVo.getOtherMessage(), "", "",
							1L, null, list, pushType)).start();
					appCustomerTokenList.clear();
				}
			}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pushUnwrapMessage(Map<String, Object> map, int pushType) {
		String pushModel;
		if (pushType == 1) {// 推送给销售经理
			AppPushMessageVo appPushMessageVo = new AppPushMessageVo();
			if (map.get("type") != null) {
				AppLendTemplate appLendTemplate = appLendTemplateDao.getAppLendTemplateMethod(BasisUtils.THREADUSER);//线索管理
					pushModel = appLendTemplate.getTempletContent();
					pushModel = pushModel.replaceAll("<name>", map.get("name").toString());
					pushModel = pushModel.replaceAll("<mobile>", map.get("mobile").toString());
			} else {
				AppLendTemplate appLendTemplate = appLendTemplateDao.getAppLendTemplateMethod(BasisUtils.UNWRAPUSER);//解绑模板
				pushModel = appLendTemplate.getTempletContent();
				String number = map.get("number").toString();// 分配数量
				pushModel = pushModel.replaceAll("<number>", number);
				}
			// 向消息表插入消息
			AppUserMessage appUserMessage = new AppUserMessage();
			appUserMessage.setSellerNumber(Integer.parseInt(map.get("sellerNumber").toString()));// 员工编号
			appUserMessage.setContext(pushModel);// 消息内容
			appUserMessage.setIsRead(2);// (2：未读；1：已读；)
			appUserMessage.setSendStatus(1);// 是否已经推送(0：未推送；1：已推送；)
			appUserMessage.setMessageType(2);// 2原来是银行
			appUserMessageDao.insertUserMessage(appUserMessage);
			logger.info("发送消息成功");
			// 查询设备
			AppUserToken appUserToken = new AppUserToken();
			appUserToken.setSellerNumber(appUserMessage.getSellerNumber());
			List<AppUserToken> list = appUserTokenDao.getAppUserToken(appUserToken);
			appPushMessageVo.setSellerNumber(appUserMessage.getSellerNumber());
			appPushMessageVo.setAppLendRequestId((long) 0);
			new Thread(
					new Runner(appPushMessageVo, pushModel, null, null, appUserMessage.getId(), list, null, "toUser"))
							.start();

		} else {// 推送给客户
			AppPushMessageVo appPushMessageVo = new AppPushMessageVo();
			AppLendTemplate appLendTemplate = appLendTemplateDao.getAppLendTemplateMethod(BasisUtils.UNWRAPCUSTOMER);//解绑模板
			pushModel = appLendTemplate.getTempletContent();
			AppCustomer appCustomer = appCustomerDao.query(Long.parseLong(map.get("uid").toString()));// 获取AppCustomer对象
			RemoteLendAppResultVo remoteLendAppResultVo = remoteLendAppUserCenterService.getUserInfoMethod(Long.parseLong(map.get("sellerNumber").toString()));
//			RemoteStaffVo remoteStaffVo = null;
//			RemoteStaffVo remoteStaffVo = swaggerService.ucId(Long.parseLong(map.get("sellerNumber").toString()));
			String customerName = appCustomer.getCustomerName();// 用户姓名
			String shopName = remoteLendAppResultVo.getShopName();// 门店名字
			String salesName = remoteLendAppResultVo.getName();// 销售姓名
			String moblie = remoteLendAppResultVo.getMobile();// 销售手机号
			// 获取进件信息
			AppLendRequest appLendRequest = appLendRequestDao.getAppLendRequestByCustomerId(Long.parseLong(map.get("uid").toString()));
			if (appLendRequest != null) {
				appPushMessageVo.setAppLendRequestId(appLendRequest.getId());
			} else {
				appPushMessageVo.setAppLendRequestId((long) 0);
			}
			// 替换模板
			pushModel = pushModel.replaceAll("<customerName>", customerName);
			pushModel = pushModel.replaceFirst("<shopName>", shopName);
			pushModel = pushModel.replaceFirst("<salesName>", salesName);
			pushModel = pushModel.replaceFirst("<salesMoblie>", moblie);
			// 向消息表插入消息
			AppCustomerMessage appCustomerMessage = new AppCustomerMessage();
			appCustomerMessage.setCustomerId(Integer.parseInt(appCustomer.getId().toString()));// uid编号
			appCustomerMessage.setContext(pushModel);// 消息内容
			appCustomerMessage.setIsRead(2);// 是否已读(2：未读；1：已读；)
			appCustomerMessage.setSendStatus(1);// 是否已经推送(0：未推送；1：已推送；)
			appCustomerMessage.setMessageType(2);// 2原来是银行
			appCustomerMessageDao.insertCustomerMessage(appCustomerMessage);
			logger.info("发送消息成功");
			// 查询设备
			AppCustomerToken appCustomerToken = new AppCustomerToken();
			appCustomerToken.setPid(appCustomer.getId().toString());
			List<AppCustomerToken> list = appCustomerTokenDao.getAppCustomerToken(appCustomerToken);
			new Thread(new Runner(appPushMessageVo, pushModel, appCustomer.getSalesName(), moblie,
					appCustomerMessage.getId(), null, list, "toCustomer")).start();
		}
	}
	
	/**
	 * 推送解绑消息
	 * @return
	 * @author lichunyue
	 * @date 2016年5月9日
	 */
	@Override
	public void pushUnwrapMessageMethod(Map<String,Object> map) {
		//推送消息
		Map<String, Object> userMap = (Map<String, Object>) map.get("user");
		this.pushUnwrapMessage(userMap,1);// 推送给销售
		List<Long> listMap = (List<Long>) map.get("customer");
		Map<String, Object> mapUid = new HashMap<>();
		for(Long uid : listMap){
			mapUid.put("uid", uid);
			mapUid.put("sellerNumber", userMap.get("sellerNumber"));
			// 推送给客户
			this.pushUnwrapMessage(mapUid,2);// 推送给客户
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
