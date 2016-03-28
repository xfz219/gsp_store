package com.puhui.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.common.constant.Constant;
import com.puhui.app.dao.ConsumingAnalysisReportDao;
import com.puhui.app.po.ConsumingAnalysisReport;
import com.puhui.app.service.ConsumingAnalysisReportService;

/**
 * @comment app耗时分析表接口实现
 * @author liwang
 * @time 2015年7月21日 下午5:43:23
 */
@Service
public class ConsumingAnalysisReportServiceImpl implements ConsumingAnalysisReportService{
	
	@Autowired
	private ConsumingAnalysisReportDao consumingAnalysisReportDao;
	
	@Override
	public List<ConsumingAnalysisReport> getConsumingAnalysisReport(
			Map<String, Object> paramMap) throws Exception {
		List<ConsumingAnalysisReport> consumList = consumingAnalysisReportDao.queryLendRequest(paramMap);
		
		if(CollectionUtils.isNotEmpty(consumList)&&consumList.size()>0){
			Map<String, Object> innerParamMap = new HashMap<String, Object>();
			innerParamMap.put("lendRequestIds", getLendRequestIds(consumList));
			//查询各种填写信息及附件耗时 重新new一个查询条件entryParamMap并增加lendRequestIds条件
			List<Map<String, Object>> entryTypeList = consumingAnalysisReportDao.queryAnnexEntryTime(innerParamMap);
			Map<String, Object> entryTypeMap = typeListTransferToMap(entryTypeList);
			//通过进件号查询贷款需求 重新new一个查询条件entryParamMap并增加lendRequestIds条件
			List<Map<String, Object>> loanDemandList = consumingAnalysisReportDao.queryLoanDemand(innerParamMap);
			Map<String, Object> loanDemandMap = listTransferToMap(loanDemandList);
			
			for(ConsumingAnalysisReport consumingAnalysisReport : consumList){
				consumingAnalysisReport.setDataTotalTime(formatTime(consumingAnalysisReport.getDataTotalTime()));

				String requestId = consumingAnalysisReport.getRequestId();
				if(entryTypeMap!=null&&StringUtils.isNotEmpty(requestId)){
					//身份信息
					Object identityTime = entryTypeMap.get(requestId+"_"+Constant.personalInfo);
					if(identityTime!=null&&!"".equals(identityTime)){
						consumingAnalysisReport.setIdentityTime(formatTime(identityTime.toString()));
					}
					//职业信息
					Object occupationTime = entryTypeMap.get(requestId+"_"+Constant.occupationInfo);
					if(occupationTime!=null&&!"".equals(occupationTime)){
						consumingAnalysisReport.setOccupationTime(formatTime(occupationTime.toString()));
					}
					//联系人信息
					Object contactTime = entryTypeMap.get(requestId+"_"+Constant.contactInfo);
					if(contactTime!=null&&!"".equals(contactTime)){
						consumingAnalysisReport.setContactTime(formatTime(contactTime.toString()));
					}
					//征信报告信息
					Object creditReportTime = entryTypeMap.get(requestId+"_"+Constant.creditReport);
					if(creditReportTime!=null&&!"".equals(creditReportTime)){
						consumingAnalysisReport.setCreditReportTime(formatTime(creditReportTime.toString()));
					}
					//淘宝账号信息
					Object taobaoTime = entryTypeMap.get(requestId+"_"+Constant.taobaoAccount);
					if(taobaoTime!=null&&!"".equals(taobaoTime)){
						consumingAnalysisReport.setTaobaoTime(formatTime(taobaoTime.toString()));
					}
					//运营商身份信息
					Object communicationTime = entryTypeMap.get(requestId+"_"+Constant.communicationInfo);
					if(communicationTime!=null&&!"".equals(communicationTime)){
						consumingAnalysisReport.setCommunicationTime(formatTime(communicationTime.toString()));
					}
					//工资流水信息
					Object payFlowTime = entryTypeMap.get(requestId+"_"+Constant.payFlow);
					if(payFlowTime!=null&&!"".equals(payFlowTime)){
						consumingAnalysisReport.setPayFlowTime(formatTime(payFlowTime.toString()));
					}
					//身份证照片信息
					Object idPhotoTime = entryTypeMap.get(requestId+"_"+Constant.idPhoto);
					if(idPhotoTime!=null&&!"".equals(idPhotoTime)){
						consumingAnalysisReport.setIdPhotoTime(formatTime(idPhotoTime.toString()));
					}
					//居住证明信息
					Object residenceCertificateTime = entryTypeMap.get(requestId+"_"+Constant.residenceCertificate);
					if(residenceCertificateTime!=null&&!"".equals(residenceCertificateTime)){
						consumingAnalysisReport.setResidenceCertificateTime(formatTime(residenceCertificateTime.toString()));
					}
					//工作证明信息
					Object workCertificateTime = entryTypeMap.get(requestId+"_"+Constant.workCertificate);
					if(workCertificateTime!=null&&!"".equals(workCertificateTime)){
						consumingAnalysisReport.setWorkCertificateTime(formatTime(workCertificateTime.toString()));
					}
					//房产证明信息
					Object estateCertificateTime = entryTypeMap.get(requestId+"_"+Constant.estateCertificate);
					if(estateCertificateTime!=null&&!"".equals(estateCertificateTime)){
						consumingAnalysisReport.setEstateCertificateTime(formatTime(estateCertificateTime.toString()));
					}
					//经营证明信息
					Object operatorCertificateTime = entryTypeMap.get(requestId+"_"+Constant.operatorCertificate);
					if(operatorCertificateTime!=null&&!"".equals(operatorCertificateTime)){
						consumingAnalysisReport.setOperatorCertificateTime(formatTime(operatorCertificateTime.toString()));
					}
					//经营地址证明信息
					Object businessAddressCertificateTime = entryTypeMap.get(requestId+"_"+Constant.businessAddressCertificate);
					if(businessAddressCertificateTime!=null&&!"".equals(businessAddressCertificateTime)){
						consumingAnalysisReport.setBusinessAddressCertificateTime(formatTime(businessAddressCertificateTime.toString()));
					}
					//已婚证明信息
					Object marriedTime = entryTypeMap.get(requestId+"_"+Constant.married);
					if(marriedTime!=null&&!"".equals(marriedTime)){
						consumingAnalysisReport.setMarriedTime(formatTime(marriedTime.toString()));
					}
					//子女证明信息
					Object childrenTime = entryTypeMap.get(requestId+"_"+Constant.children);
					if(childrenTime!=null&&!"".equals(childrenTime)){
						consumingAnalysisReport.setChildrenTime(formatTime(childrenTime.toString()));
					}
					//学历证明信息
					Object degreeTime = entryTypeMap.get(requestId+"_"+Constant.degree);
					if(degreeTime!=null&&!"".equals(degreeTime)){
						consumingAnalysisReport.setDegreeTime(formatTime(degreeTime.toString()));
					}
					//社保|公积金证明信息
					Object providentSocialTime = entryTypeMap.get(requestId+"_"+Constant.providentSocial);
					if(providentSocialTime!=null&&!"".equals(providentSocialTime)){
						consumingAnalysisReport.setProvidentSocialTime(formatTime(providentSocialTime.toString()));
					}
					//其他证明信息
					Object otherTime = entryTypeMap.get(requestId+"_"+Constant.other);
					if(otherTime!=null&&!"".equals(otherTime)){
						consumingAnalysisReport.setOtherTime(formatTime(otherTime.toString()));
					}
				}
				if(loanDemandMap!=null&&StringUtils.isNotEmpty(requestId)){
					//产品更换次数
					Object productUpdateCount = loanDemandMap.get(requestId);
					if(productUpdateCount!=null&&!"".equals(productUpdateCount)){
						consumingAnalysisReport.setProductUpdateCount(Constant.productUpdateCount-Integer.parseInt(productUpdateCount.toString()));
					}
				}
			}
		}
		return consumList;
	}

	/**
	 * @comment 将 List<Map<String, Object>> 转换成key=lendRequestId_entryType value=dataTotalTime 形式的map
	 * @returned Map<String,Object>
	 * @author liwang
	 * @time 2015年7月22日 上午10:15:52
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> typeListTransferToMap(List<Map<String, Object>> list)throws Exception{
		Map<String, Object> map = null;
		if(CollectionUtils.isNotEmpty(list)&&list.size()>0){
			map = new HashMap<String, Object>();
			for(Map<String, Object> entryMap : list){
				if(entryMap.get("app_lend_request_id")!=null&&!"".equals(entryMap.get("app_lend_request_id"))&&
					entryMap.get("entry_type")!=null&&!"".equals(entryMap.get("entry_type"))){
					map.put(entryMap.get("app_lend_request_id")+"_"+entryMap.get("entry_type"), entryMap.get("data_total_time"));
				}
			}
		}
		return map;
	}
	
	/**
	 * @comment 将 List<Map<String, Object>> 转换成key=lendRequestId value=dataTotalTime 形式的map
	 * @returned Map<String,Object>
	 * @author liwang
	 * @time 2015年7月24日 上午11:35:53
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> listTransferToMap(List<Map<String, Object>> list)throws Exception{
		Map<String, Object> map = null;
		if(CollectionUtils.isNotEmpty(list)&&list.size()>0){
			map = new HashMap<String, Object>();
			for(Map<String, Object> entryMap : list){
				if(entryMap.get("app_lend_request_id")!=null&&!"".equals(entryMap.get("app_lend_request_id"))){
					map.put(entryMap.get("app_lend_request_id").toString(), entryMap.get("count"));
				}
			}
		}
		return map;
	}
	
	/**
	 * @comment 获取进件号集合
	 * @returned List<String>
	 * @author liwang
	 * @time 2015年7月22日 上午10:23:07
	 * @param consumingAnalysisReportList
	 * @return
	 * @throws Exception
	 */
	private List<String> getLendRequestIds(List<ConsumingAnalysisReport> consumingAnalysisReportList)throws Exception{
		if(CollectionUtils.isNotEmpty(consumingAnalysisReportList)&&consumingAnalysisReportList.size()>0){
			List<String> list = new ArrayList<String>();
			for(ConsumingAnalysisReport consumingAnalysisReport : consumingAnalysisReportList){
				if(StringUtils.isNotEmpty(consumingAnalysisReport.getRequestId()))list.add(consumingAnalysisReport.getRequestId());
			}
			return list;
		}
		return null;
	}
	
	/**
	 * @comment 时间格式化为##天##小时##分##秒
	 * @returned String
	 * @author liwang
	 * @time 2015年7月22日 上午11:15:44
	 * @param totalSeconds 总时间  秒为单位
	 * @return
	 * @throws Exception
	 */
	private String formatTime(String totalSeconds) throws Exception{
		if(StringUtils.isEmpty(totalSeconds))return "";
		long toalTime = Long.parseLong(totalSeconds);
		long hours = toalTime / (60 * 60);
		long minutes = (toalTime % (60 * 60)) / (60);
		long seconds = toalTime % 60;
		
		return hours + "小时" + minutes + "分" + seconds + "秒";
	}
	
}
