package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import com.puhui.app.utils.SensitiveInfoUtils;
import com.puhui.cc.cloud.api.vo.LendLossDataVo;
import com.puhui.cc.cloud.api.vo.ResultVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.puhui.app.dao.AppCustomerDao;
import com.puhui.app.dao.AppInterfaceLogDao;
import com.puhui.app.dao.AppUserToPromoteDao;
import com.puhui.app.po.AppUserToPromote;
import com.puhui.app.service.AppPushService;
import com.puhui.app.service.CustomerCluesService;
import com.puhui.app.service.SwaggerService;
import com.puhui.app.utils.CitySet;
import com.puhui.app.utils.DateUtil;
import com.puhui.app.utils.LendAesUtil;
import com.puhui.uc.vo.RemoteOrganizationVo;
import com.puhui.uc.vo.RemoteStaffVo;

import net.sf.json.JSONArray;

@Service
public class CustomerCluesServiceImpl implements CustomerCluesService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerCluesServiceImpl.class);
    private static final String SHOPCODE = "RPA6220102,RPA2530301,RPA5370201,RPA3530501";
    public static final String LEND_VALIDATE_CHANNEL = "666";
    @Autowired
    private AppUserToPromoteDao appUserToPromoteDao;
    @Autowired
    private AppCustomerDao appCustomerDao;
    @Autowired
    private AppInterfaceLogDao appInterfaceLogDao;
    @Autowired
    private SwaggerService swaggerService;
    @Autowired
    private AppPushService appPushService;

    /**
     * @return
     * @comment 线索查询
     * @author lichunyue
     */
    @Override
    public List<Map<String, Object>> selectCustomerCluesMethod(Map<String, Object> paramMap) throws Exception {
        List<AppUserToPromote> autpList = appUserToPromoteDao.selectCustomerCluesMethod(paramMap);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String shareGuidance = "客户经理";//引导分享
        for (AppUserToPromote autp : autpList) {
            Map<String, Object> autpMap = new HashMap<String, Object>();
            autpMap.put("toPromoteId", autp.getId());
            autpMap.put("channel", autp.getChannel());
            autpMap.put("channelTwo", autp.getChannelTwo());

            //脱敏
            String name = autp.getName();
            name = SensitiveInfoUtils.sensitiveName(name);
            String mobile = LendAesUtil.decrypt(autp.getMobile());
            mobile = SensitiveInfoUtils.sensitiveMobile(mobile);
            autpMap.put("name", name);
            autpMap.put("mobile", mobile);

            autpMap.put("city", autp.getCity());
            autpMap.put("branch", autp.getBranch());
            autpMap.put("branchCode", autp.getBranchCode());
            autpMap.put("salesName", autp.getSalesName());
            autpMap.put("salesNo", autp.getSalesNo());
            autpMap.put("department", autp.getDepartment());
            List<Map<String, Object>> listMap = appCustomerDao.getMobileMethod(autp.getMobile());
            autpMap.put("registered", listMap.isEmpty() ? "未注册" : "已注册");
            autpMap.put("sales", autp.getSalesNo() == null ? "否" : "是");
            autpMap.put("salesStatus", autp.getSalesNo() == null ? "" : getUserInfoMethod(autp.getSalesNo(), autp.getCityCode()));
            if (autp.getShareGuidance() != null) {
                switch (autp.getShareGuidance()) {
                    case 2:
                        shareGuidance = "客服引导";
                        break;
                    case 3:
                        shareGuidance = "自发分享";
                        break;
                    default:
                        break;
                }
            }
            autpMap.put("shareGuidance", shareGuidance);
            autpMap.put("createTime", DateUtil.toDateTimeString(autp.getCreateTime()));
            autpMap.put("updateAllotTime", DateUtil.toDateTimeString(autp.getUpdateAllotTime()));
            list.add(autpMap);
        }
        return list;
    }

    @Override
    public void updateBindingUserMethod(AppUserToPromote appUserToPromote, String selectUserName) {
        RemoteStaffVo remoteStaffVo = swaggerService.employeeNo(selectUserName);
        String cityCode = remoteStaffVo.getOrganizationVo().getParentVo().getCode();
        cityCode = cityCode.substring(0, cityCode.length() - 2);
        List<RemoteOrganizationVo> list = swaggerService.like(cityCode);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> userMap = new HashMap<String, Object>();
        try {
            map.put("id", appUserToPromote.getId());
            map.put("name", remoteStaffVo.getRealName());
            map.put("salesNo", selectUserName);
            map.put("department", remoteStaffVo.getOrganizationVo().getName());
            map.put("city", list.get(0).getName());
            map.put("cityCode", cityCode);
            map.put("branch", remoteStaffVo.getOrganizationVo().getParentVo().getName());
            map.put("branchCode", remoteStaffVo.getOrganizationVo().getParentVo().getCode());
            appUserToPromoteDao.updateBindingUserMethod(map);
            userMap.put("type", 2);
            userMap.put("name", appUserToPromote.getName());
            userMap.put("mobile", appUserToPromote.getMobile());
            userMap.put("sellerNumber", remoteStaffVo.getId());
            appPushService.pushUnwrapMessage(userMap, 1);// 推送给销售
        } catch (Exception e) {
            logger.info("接收cc推送其它渠道数据出现异常");
        }
    }

    /**
     * @return
     * @throws Exception
     * @comment 查询是否异常
     * @author lichunyue
     */
    @Override
    public String getUserInfoMethod(String salesNo, String cityCode) throws Exception {
        boolean salesStatus = false;//false异常/true正常
        RemoteStaffVo remoteStaffVo = swaggerService.employeeNo(salesNo);
        if (remoteStaffVo != null && remoteStaffVo.getEnabled() == true
                && remoteStaffVo.getPositionName().equals("个贷-销售")
                && getUserCityNameMethod(cityCode.trim(), remoteStaffVo.getOrganizationVo().getParentVo().getCode())) {
            salesStatus = true;
        }
        return salesStatus == false ? "异常" : "正常";
    }

    /**
     * @return
     * @comment 查询城市
     * @author lichunyue
     */
    public boolean getUserCityNameMethod(String cityCode, String shopCode) throws Exception {
        boolean cityName = true;
        char[] cityC = cityCode.toCharArray();
        char[] shopNameC = shopCode.toCharArray();
        for (int i = 0; i < cityC.length; i++) {
            if (cityC[i] != shopNameC[i]) {
                cityName = false;
                break;
            }
        }
        return cityName;
    }

    /**
     * @return
     * @comment 查询销售
     * @author lichunyue
     */
    @Override
    public JSONArray selectUserNameMethod(String department, String shopCode) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        RemoteStaffVo remoteStaffVo = new RemoteStaffVo();
        RemoteOrganizationVo organizationVo = new RemoteOrganizationVo();
        organizationVo.setCode(shopCode + "%");
//		organizationVo.setName(department);
        remoteStaffVo.setOrganizationVo(organizationVo);
        remoteStaffVo.setEnabled(true);//在职
        remoteStaffVo.setPositionType("SALES");//销售
        List<RemoteStaffVo> remoteStaffVoList = swaggerService.ucPage(0, 0, remoteStaffVo);
        for (RemoteStaffVo remoteStaffVoName : remoteStaffVoList) {
            if (remoteStaffVoName.getOrganizationVo().getName().equals(department)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("selectUserCode", remoteStaffVoName.getEmployeeNo());
                map.put("selectUserName", remoteStaffVoName.getRealName());
                listMap.add(map);
            }
        }
        return JSONArray.fromObject(listMap);
    }

    @Override
    /**
     *
     * 查看客户详细信息
     *
     */
    public AppUserToPromote findCustomerCluesMethod(Long id) {
        AppUserToPromote au = appUserToPromoteDao.findCustomerCluesMethod(id);
        return au;
    }

    /**
     * lcy
     * 接受获客
     */
    @Override
    public void insertAppUserToPromote(JSONObject jsonObject) {
        try {
            AppUserToPromote appUserToPromote = new AppUserToPromote();

            //第一步数据过滤并且分配门店
            String idNo = LendAesUtil.encrypt(jsonObject.getString("idNo"));
            String mobile = LendAesUtil.encrypt(jsonObject.getString("telNumber"));
            String chanceType = jsonObject.getString("chanceType");
            if(LEND_VALIDATE_CHANNEL.equals(chanceType)){
                // 渠道为电销导流
                if (this.getUserInfoMobile(mobile) > 0) {
                    logger.info("个贷验证导流推送数据手机号已经存在,过滤数据为{}", jsonObject.toJSONString());
                    return;
                }
                List<Map<String, Object>> customerList = appCustomerDao.getIdNoMethod(idNo);
                if(CollectionUtils.isNotEmpty(customerList)){
                    logger.info("个贷验证导流推送数据身份证号已经注册，过滤数据为{}", jsonObject.toJSONString());
                    return;
                }
                appUserToPromote.setCity(jsonObject.getString("city"));
                appUserToPromote.setCityCode(jsonObject.getString("cityCode"));
                appUserToPromote.setBranch("电销门店");
                appUserToPromote.setBranchCode("RPA8010101");
            } else {
                if (this.getUserInfoIdNo(idNo) > 0 || this.getUserInfoMobile(mobile) > 0) {
                    logger.info("推送数据身份证号手机号重复,重复数据为{}", jsonObject.toJSONString());
                    return;
                }
                Map<String, Object> cityMap = new HashMap<>();
                List<RemoteOrganizationVo> listRo = new ArrayList<>();
                List<RemoteOrganizationVo> list = swaggerService.like("rpa");
                List<Map<String, Object>> listMapCity = CitySet.getCityMap(list);
                for (Map<String, Object> listMapCityMap : listMapCity) {
                    if (jsonObject.getString("city").equals(listMapCityMap.get("cityName"))
                            || jsonObject.getString("city").equals(listMapCityMap.get("cityName") + "市")) {
                        cityMap = listMapCityMap;
                        List<RemoteOrganizationVo> listShop = swaggerService.orgIdSub(Long.parseLong(String.valueOf(listMapCityMap.get("id"))));
                        for (RemoteOrganizationVo ro : listShop) {
                            if (SHOPCODE.contains(ro.getCode())) {
                                continue;
                            }
                            listRo.add(ro);
                        }
                        break;
                    }
                }
                Random r = new Random();
                RemoteOrganizationVo lsv = listRo.get(r.nextInt(listRo.size()));
                appUserToPromote.setBranch(lsv.getName());
                appUserToPromote.setBranchCode(lsv.getCode());
                appUserToPromote.setCity(String.valueOf(cityMap.get("cityName")));
                appUserToPromote.setCityCode(String.valueOf(cityMap.get("cityCode")));
            }

            //第二步保存意向客户
            appUserToPromote.setAmount(jsonObject.getBigDecimal("applyAmount"));
            appUserToPromote.setName(jsonObject.getString("customerName"));
            appUserToPromote.setProvince(jsonObject.getString("province"));
            appUserToPromote.setProductName(jsonObject.getString("productName"));
            appUserToPromote.setIdNo(idNo);
            appUserToPromote.setChannel(jsonObject.getString("chanceType"));
            Map<String, Object> map = this.findChannel(jsonObject.getString("chanceType"));
            appUserToPromote.setChannelType(String.valueOf(map.get("codeValue")));
            appUserToPromote.setChannelTwoType(String.valueOf(map.get("channelTwoCode")));
            appUserToPromote.setMobile(mobile);
            appUserToPromote.setIsSettle(jsonObject.getBoolean("settle"));
            appUserToPromote.setSettleTime(jsonObject.getDate("settleTime"));
            appUserToPromoteDao.insertAppUserToPromote(appUserToPromote);

            //第三步如果是电销导流异步推送
            if(LEND_VALIDATE_CHANNEL.equals(chanceType)) {
                LendLossDataVo lendLossDataVo = new LendLossDataVo();
                lendLossDataVo.setCustomerName(appUserToPromote.getName());
                lendLossDataVo.setIdNo(appUserToPromote.getIdNo());
                lendLossDataVo.setCity(appUserToPromote.getCityCode());
                lendLossDataVo.setMobile(appUserToPromote.getMobile());
                lendLossDataVo.setStore(jsonObject.getString("shopCode"));
                asyncPushCC(lendLossDataVo);
            }

            logger.info("接收cc推送其它渠道数据结束");
        } catch (Exception e) {
            logger.info("接收cc推送其它渠道数据出现异常: ", e);
            Map<String, Object> map = new HashMap<>();
            map.put("interfaceType", 3);
            map.put("interfaceTypeName", "接收cc推送数据接口出现异常");
            map.put("isSuccess", 1);
            map.put("message", jsonObject.toJSONString());
            appInterfaceLogDao.insertLog(map);
        }
    }

    /**
     * 异步推送电销
     */
    public void asyncPushCC(LendLossDataVo lendLossDataVo){
        CompletableFuture.runAsync(() -> {
            logger.info("电销导流传入参数：{}", JSONObject.toJSONString(lendLossDataVo));
            ResultVo resultVo = swaggerService.ccReceiveLendLossData(lendLossDataVo);
            logger.info("电销导流回结果：{}", JSONObject.toJSONString(resultVo));
        });
    }

    @Override
    public List<Map<String, Object>> findChannels() {
        return appUserToPromoteDao.findChannels();
    }

    public Map<String, Object> findChannel(String channel) {
        return appUserToPromoteDao.findChannel(channel);
    }

    @Override
    public int getUserInfoIdNo(String idNo) {
        return appUserToPromoteDao.findCustomerCluesMethodIdNo(idNo);
    }

    @Override
    public int getUserInfoMobile(String mobile) {
        return appUserToPromoteDao.findCustomerCluesMethodMobile(mobile);
    }
}
