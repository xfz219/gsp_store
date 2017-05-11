package com.puhui.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puhui.app.common.constant.Constant;
import com.puhui.app.dao.ProcessAnalysisReportDao;
import com.puhui.app.po.ProcessAnalysisReport;
import com.puhui.app.service.ProcessAnalysisReportService;

/**
 * @comment app全流程分析表接口实现
 * @author liwang
 * @time 2015年7月20日 上午10:46:34
 */
@Service
public class ProcessAnalysisReportServiceImpl implements
		ProcessAnalysisReportService {
	
	@Autowired
	private ProcessAnalysisReportDao processAnalysisReportDao;
	
	
	@Override
	public List<ProcessAnalysisReport> getProcessAnalysisReport(Map<String, Object> paramMap) throws Exception{
		List<ProcessAnalysisReport> processAnalysisReportList = processAnalysisReportDao.queryRegisteredCount(paramMap);
		if(CollectionUtils.isNotEmpty(processAnalysisReportList)&&processAnalysisReportList.size()>0){
			Map<String, Object> innerParamMap = new HashMap<String, Object>();
			innerParamMap.put("saleNums", getSaleNums(processAnalysisReportList));
			//查询各种类型信息数据 重新new一个查询条件innerParamMap并增加saleNums条件
			List<Map<String, Object>> typetList= processAnalysisReportDao.queryAnnexStatusTypeCount(innerParamMap);
			Map<String, Object> typeMap = typeListTransferToMap(typetList);//转换成key=saleNum_type value=count 的map
			//查询已完成录入所有信息件数 重新new一个查询条件innerParamMap并增加saleNums条件
			List<Map<String, Object>> inputCompletionList= processAnalysisReportDao.queryRequestInputCompletionCount(innerParamMap);
			Map<String, Object> inputCompletionMap = listTransferToMap(inputCompletionList);//转换成key=saleNum value=count 的map
			//查询销售质检完成件数 重新new一个查询条件innerParamMap并增加saleNums条件
			List<Map<String, Object>> checkCompletionList= processAnalysisReportDao.queryRequestStateCount(innerParamMap);
			Map<String, Object> checkCompletionMap = listTransferToMap(checkCompletionList);//转换成key=saleNum value=count 的map

			
			for(ProcessAnalysisReport processAnalysisReport : processAnalysisReportList){
				String saleNum = processAnalysisReport.getSaleNum();
				
				if(typeMap!=null&&StringUtils.isNotEmpty(saleNum)){
					//身份信息
					Object identityCount = typeMap.get(saleNum+"_"+Constant.personalInfo);
					if(identityCount!=null&&!"".equals(identityCount)){
						processAnalysisReport.setIdentityCount(Long.parseLong(identityCount.toString()));//完成数
						processAnalysisReport.setIdentityRate(new BigDecimal(identityCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//职业信息
					Object occupationCount = typeMap.get(saleNum+"_"+Constant.occupationInfo);
					if(occupationCount!=null&&!"".equals(occupationCount)){
						processAnalysisReport.setOccupationCount(Long.parseLong(occupationCount.toString()));//完成数
						processAnalysisReport.setOccupationRate(new BigDecimal(occupationCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//联系人信息
					Object contactCount = typeMap.get(saleNum+"_"+Constant.contactInfo);
					if(contactCount!=null&&!"".equals(contactCount)){
						processAnalysisReport.setContactCount(Long.parseLong(contactCount.toString()));//完成数
						processAnalysisReport.setContactRate(new BigDecimal(contactCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//征信报告信息
					Object creditReportCount = typeMap.get(saleNum+"_"+Constant.creditReport);
					if(creditReportCount!=null&&!"".equals(creditReportCount)){
						processAnalysisReport.setCreditReportCount(Long.parseLong(creditReportCount.toString()));//完成数
						processAnalysisReport.setCreditReportRate(new BigDecimal(creditReportCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//淘宝账号信息
					Object taobaoCount = typeMap.get(saleNum+"_"+Constant.taobaoAccount);
					if(taobaoCount!=null&&!"".equals(taobaoCount)){
						processAnalysisReport.setTaobaoCount(Long.parseLong(taobaoCount.toString()));//完成数
						processAnalysisReport.setTaobaoRate(new BigDecimal(taobaoCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//运营商身份信息
					Object communicationCount = typeMap.get(saleNum+"_"+Constant.communicationInfo);
					if(communicationCount!=null&&!"".equals(communicationCount)){
						processAnalysisReport.setCommunicationCount(Long.parseLong(communicationCount.toString()));//完成数
						processAnalysisReport.setCommunicationRate(new BigDecimal(communicationCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//工资流水信息
					Object payFlowCount = typeMap.get(saleNum+"_"+Constant.payFlow);
					if(payFlowCount!=null&&!"".equals(payFlowCount)){
						processAnalysisReport.setPayFlowCount(Long.parseLong(payFlowCount.toString()));//完成数
						processAnalysisReport.setPayFlowRate(new BigDecimal(payFlowCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//身份证照片信息
					Object idPhotoCount = typeMap.get(saleNum+"_"+Constant.idPhoto);
					if(idPhotoCount!=null&&!"".equals(idPhotoCount)){
						processAnalysisReport.setIdPhotoCount(Long.parseLong(idPhotoCount.toString()));//完成数
						processAnalysisReport.setIdPhotoRate(new BigDecimal(idPhotoCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//居住证明信息
					Object residenceCertificateCount = typeMap.get(saleNum+"_"+Constant.residenceCertificate);
					if(residenceCertificateCount!=null&&!"".equals(residenceCertificateCount)){
						processAnalysisReport.setResidenceCertificateCount(Long.parseLong(residenceCertificateCount.toString()));//完成数
						processAnalysisReport.setResidenceCertificateRate(new BigDecimal(residenceCertificateCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//工作证明信息
					Object workCertificateCount = typeMap.get(saleNum+"_"+Constant.workCertificate);
					if(workCertificateCount!=null&&!"".equals(workCertificateCount)){
						processAnalysisReport.setWorkCertificateCount(Long.parseLong(workCertificateCount.toString()));//完成数
						processAnalysisReport.setWorkCertificateRate(new BigDecimal(workCertificateCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//房产证明信息
					Object estateCertificateCount = typeMap.get(saleNum+"_"+Constant.estateCertificate);
					if(estateCertificateCount!=null&&!"".equals(estateCertificateCount)){
						processAnalysisReport.setEstateCertificateCount(Long.parseLong(estateCertificateCount.toString()));//完成数
						processAnalysisReport.setEstateCertificateRate(new BigDecimal(estateCertificateCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//经营证明信息
					Object operatorCertificateCount = typeMap.get(saleNum+"_"+Constant.operatorCertificate);
					if(operatorCertificateCount!=null&&!"".equals(operatorCertificateCount)){
						processAnalysisReport.setOperatorCertificateCount(Long.parseLong(operatorCertificateCount.toString()));//完成数
						processAnalysisReport.setOperatorCertificateRate(new BigDecimal(operatorCertificateCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//经营地址证明信息
					Object businessAddressCertificateCount = typeMap.get(saleNum+"_"+Constant.businessAddressCertificate);
					if(businessAddressCertificateCount!=null&&!"".equals(businessAddressCertificateCount)){
						processAnalysisReport.setBusinessAddressCertificateCount(Long.parseLong(businessAddressCertificateCount.toString()));//完成数
						processAnalysisReport.setBusinessAddressCertificateRate(new BigDecimal(businessAddressCertificateCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//已婚证明信息
					Object marriedCount = typeMap.get(saleNum+"_"+Constant.married);
					if(marriedCount!=null&&!"".equals(marriedCount)){
						processAnalysisReport.setMarriedCount(Long.parseLong(marriedCount.toString()));//完成数
						processAnalysisReport.setMarriedRate(new BigDecimal(marriedCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//子女证明信息
					Object childrenCount = typeMap.get(saleNum+"_"+Constant.children);
					if(childrenCount!=null&&!"".equals(childrenCount)){
						processAnalysisReport.setChildrenCount(Long.parseLong(childrenCount.toString()));//完成数
						processAnalysisReport.setChildrenRate(new BigDecimal(childrenCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//学历证明信息
					Object degreeCount = typeMap.get(saleNum+"_"+Constant.degree);
					if(degreeCount!=null&&!"".equals(degreeCount)){
						processAnalysisReport.setDegreeCount(Long.parseLong(degreeCount.toString()));//完成数
						processAnalysisReport.setDegreeRate(new BigDecimal(degreeCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//社保|公积金证明信息
					Object providentSocialCount = typeMap.get(saleNum+"_"+Constant.providentSocial);
					if(providentSocialCount!=null&&!"".equals(providentSocialCount)){
						processAnalysisReport.setProvidentSocialCount(Long.parseLong(providentSocialCount.toString()));//完成数
						processAnalysisReport.setProvidentSocialRate(new BigDecimal(providentSocialCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
					//其他证明信息
					Object otherCount = typeMap.get(saleNum+"_"+Constant.other);
					if(otherCount!=null&&!"".equals(otherCount)){
						processAnalysisReport.setOtherCount(Long.parseLong(otherCount.toString()));//完成数
						processAnalysisReport.setOtherRate(new BigDecimal(otherCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
				}
				
				if(inputCompletionMap!=null&&StringUtils.isNotEmpty(saleNum)){
					//客户完成录入提交销售质检数
					Object inputCompletionCount = inputCompletionMap.get(saleNum);
					if(inputCompletionCount!=null&&!"".equals(inputCompletionCount)){
						processAnalysisReport.setInputCompletionCount(Long.parseLong(inputCompletionCount.toString()));//完成数
						processAnalysisReport.setInputCompletionRate(new BigDecimal(inputCompletionCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
				}
				if(checkCompletionMap!=null&&StringUtils.isNotEmpty(saleNum)){
					//销售质检完成件数
					Object checkCompletionCount = checkCompletionMap.get(saleNum);
					if(checkCompletionCount!=null&&!"".equals(checkCompletionCount)){
						processAnalysisReport.setCheckCompletionCount(Long.parseLong(checkCompletionCount.toString()));//完成数
						processAnalysisReport.setCheckCompletionRate(new BigDecimal(checkCompletionCount.toString())//完成率
						.multiply(new BigDecimal("100")).divide(new BigDecimal(processAnalysisReport.getRegistCount()+""), 2, BigDecimal.ROUND_HALF_UP)+"%");
					}
				}
			}
		}
		
		return processAnalysisReportList;
	}

	/**
	 * @comment 将 List<Map<String, Object>> 转换成key=saleNum_type value=count 形式的map
	 * @returned Map<String,Object>
	 * @author liwang
	 * @time 2015年7月20日 下午4:41:52
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> typeListTransferToMap(List<Map<String, Object>> list)throws Exception{
		Map<String, Object> map = null;
		if(CollectionUtils.isNotEmpty(list)&&list.size()>0){
			map = new HashMap<>();
			for(Map<String, Object> typeMap : list){
				if(typeMap.get("sale_num")!=null&&!"".equals(typeMap.get("sale_num"))&&
				   typeMap.get("type")!=null&&!"".equals(typeMap.get("type"))){
					map.put(typeMap.get("sale_num").toString()+"_"+typeMap.get("type").toString(), typeMap.get("count"));
				}
			}
		}
		return map;
	}
	
	/**
	 * @comment 将 List<Map<String, Object>> 转换成key=saleNum value=count 形式的map
	 * @returned Map<String,Object>
	 * @author liwang
	 * @time 2015年7月22日 下午3:28:44
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> listTransferToMap(List<Map<String, Object>> list)throws Exception{
		Map<String, Object> map = null;
		if(CollectionUtils.isNotEmpty(list)){
			map = new HashMap<>();
			for(Map<String, Object> typeMap : list){
				if(typeMap.get("sale_num")!=null&&!"".equals(typeMap.get("sale_num"))){
					map.put(typeMap.get("sale_num").toString(), typeMap.get("count"));
				}
			}
		}
		return map;
	}
	
	/**
	 * @comment 获取员工号集合
	 * @returned List<String>
	 * @author liwang
	 * @time 2015年7月20日 下午5:10:02
	 * @param processAnalysisReportList
	 * @return
	 * @throws Exception
	 */
	private List<String> getSaleNums(List<ProcessAnalysisReport> processAnalysisReportList) throws Exception {
		if (CollectionUtils.isNotEmpty(processAnalysisReportList)) {
			List<String> list = new ArrayList<>();
			for (ProcessAnalysisReport processAnalysisReport : processAnalysisReportList) {
				if (StringUtils.isNotEmpty(processAnalysisReport.getSaleNum())){
					list.add(processAnalysisReport.getSaleNum());
				}
			}
			return list;
		}
		return null;
	}

}
