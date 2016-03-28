package com.puhui.app.po;

import java.io.Serializable;

/**
 * @comment app全流程分析表返回数据实体类
 * @author liwang
 * @time 2015年7月20日 上午10:28:34
 */
public class ProcessAnalysisReport implements Serializable{
	private static final long serialVersionUID = 6865182755796976787L;

	private String saleNum;//销售人员编号
	private String salesNo;//员工编号
	private String salesName;//销售人员姓名
	private long registCount;//注册客户数量
	private long identityCount;//完成身份信息用户数量
	private String identityRate = "0.00%";//身份信息完成率
	private long occupationCount;//完成职业信息用户数量
	private String occupationRate = "0.00%";//职业信息完成率
	private long contactCount;//完成联系人信息用户数量
	private String contactRate = "0.00%";//联系人信息完成率
	private long creditReportCount;//完成征信报告信息用户数量
	private String creditReportRate = "0.00%";//征信报告信息完成率
	private long taobaoCount;//完成淘宝信息用户数量
	private String taobaoRate = "0.00%";//淘宝信息完成率
	private long communicationCount;//完成运营商信息用户数量
	private String communicationRate = "0.00%";//运营商信息完成率
	private long payFlowCount;//工资流水信息用户数量
	private String payFlowRate = "0.00%";//工资流水信息完成率
	private long idPhotoCount;//完成身份证照片用户数量
	private String idPhotoRate = "0.00%";//身份证照片完成率
	private long residenceCertificateCount;//完成居住证明信息用户数量
	private String residenceCertificateRate = "0.00%";//居住证明信息完成率
	private long workCertificateCount;//完成工作证明信息用户数量
	private String workCertificateRate = "0.00%";//工作证明信息完成率
	private long estateCertificateCount;//完成房产证明信息用户数量
	private String estateCertificateRate = "0.00%";//房产证明信息完成率
	private long operatorCertificateCount;//完成经营证明信息用户数量
	private String operatorCertificateRate = "0.00%";//经营证明信息完成率
	private long businessAddressCertificateCount;//完成经营地址证明信息用户数量
	private String businessAddressCertificateRate = "0.00%";//经营地址证明信息完成率
	private long marriedCount;//完成已婚证明信息用户数量
	private String marriedRate = "0.00%";//已婚证明信息完成率
	private long childrenCount;//完成子女证明信息用户数量
	private String childrenRate = "0.00%";//子女证明信息完成率
	private long degreeCount;//完成学历证明信息用户数量
	private String degreeRate = "0.00%";//学历证明信息完成率
	private long providentSocialCount;//完成社保公积金信息用户数量
	private String providentSocialRate = "0.00%";//社保公积金信息完成率
	private long otherCount;//完成其他证明信息用户数量
	private String otherRate = "0.00%";//其他证明信息完成率
	private long inputCompletionCount;//客户完成录入提交销售质检件数
	private String inputCompletionRate = "0.00%";//客户完成录入提交销售质检件完成率
	private long checkCompletionCount;//销售质检完成件数
	private String checkCompletionRate = "0.00%";//销售质检完成完成率
	
	public String getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}
	public long getIdentityCount() {
		return identityCount;
	}
	public void setIdentityCount(long identityCount) {
		this.identityCount = identityCount;
	}
	public long getRegistCount() {
		return registCount;
	}
	public void setRegistCount(long registCount) {
		this.registCount = registCount;
	}
	public String getIdentityRate() {
		return identityRate;
	}
	public void setIdentityRate(String identityRate) {
		this.identityRate = identityRate;
	}
	public long getOccupationCount() {
		return occupationCount;
	}
	public void setOccupationCount(long occupationCount) {
		this.occupationCount = occupationCount;
	}
	public String getOccupationRate() {
		return occupationRate;
	}
	public void setOccupationRate(String occupationRate) {
		this.occupationRate = occupationRate;
	}
	public long getContactCount() {
		return contactCount;
	}
	public void setContactCount(long contactCount) {
		this.contactCount = contactCount;
	}
	public String getContactRate() {
		return contactRate;
	}
	public void setContactRate(String contactRate) {
		this.contactRate = contactRate;
	}
	public long getCreditReportCount() {
		return creditReportCount;
	}
	public void setCreditReportCount(long creditReportCount) {
		this.creditReportCount = creditReportCount;
	}
	public String getCreditReportRate() {
		return creditReportRate;
	}
	public void setCreditReportRate(String creditReportRate) {
		this.creditReportRate = creditReportRate;
	}
	public long getTaobaoCount() {
		return taobaoCount;
	}
	public void setTaobaoCount(long taobaoCount) {
		this.taobaoCount = taobaoCount;
	}
	public String getTaobaoRate() {
		return taobaoRate;
	}
	public void setTaobaoRate(String taobaoRate) {
		this.taobaoRate = taobaoRate;
	}
	public long getCommunicationCount() {
		return communicationCount;
	}
	public void setCommunicationCount(long communicationCount) {
		this.communicationCount = communicationCount;
	}
	public String getCommunicationRate() {
		return communicationRate;
	}
	public void setCommunicationRate(String communicationRate) {
		this.communicationRate = communicationRate;
	}
	public long getPayFlowCount() {
		return payFlowCount;
	}
	public void setPayFlowCount(long payFlowCount) {
		this.payFlowCount = payFlowCount;
	}
	public String getPayFlowRate() {
		return payFlowRate;
	}
	public void setPayFlowRate(String payFlowRate) {
		this.payFlowRate = payFlowRate;
	}
	public long getIdPhotoCount() {
		return idPhotoCount;
	}
	public void setIdPhotoCount(long idPhotoCount) {
		this.idPhotoCount = idPhotoCount;
	}
	public String getIdPhotoRate() {
		return idPhotoRate;
	}
	public void setIdPhotoRate(String idPhotoRate) {
		this.idPhotoRate = idPhotoRate;
	}
	public long getResidenceCertificateCount() {
		return residenceCertificateCount;
	}
	public void setResidenceCertificateCount(long residenceCertificateCount) {
		this.residenceCertificateCount = residenceCertificateCount;
	}
	public String getResidenceCertificateRate() {
		return residenceCertificateRate;
	}
	public void setResidenceCertificateRate(String residenceCertificateRate) {
		this.residenceCertificateRate = residenceCertificateRate;
	}
	public long getWorkCertificateCount() {
		return workCertificateCount;
	}
	public void setWorkCertificateCount(long workCertificateCount) {
		this.workCertificateCount = workCertificateCount;
	}
	public String getWorkCertificateRate() {
		return workCertificateRate;
	}
	public void setWorkCertificateRate(String workCertificateRate) {
		this.workCertificateRate = workCertificateRate;
	}
	public long getEstateCertificateCount() {
		return estateCertificateCount;
	}
	public void setEstateCertificateCount(long estateCertificateCount) {
		this.estateCertificateCount = estateCertificateCount;
	}
	public String getEstateCertificateRate() {
		return estateCertificateRate;
	}
	public void setEstateCertificateRate(String estateCertificateRate) {
		this.estateCertificateRate = estateCertificateRate;
	}
	public long getOperatorCertificateCount() {
		return operatorCertificateCount;
	}
	public void setOperatorCertificateCount(long operatorCertificateCount) {
		this.operatorCertificateCount = operatorCertificateCount;
	}
	public String getOperatorCertificateRate() {
		return operatorCertificateRate;
	}
	public void setOperatorCertificateRate(String operatorCertificateRate) {
		this.operatorCertificateRate = operatorCertificateRate;
	}
	public long getBusinessAddressCertificateCount() {
		return businessAddressCertificateCount;
	}
	public void setBusinessAddressCertificateCount(
			long businessAddressCertificateCount) {
		this.businessAddressCertificateCount = businessAddressCertificateCount;
	}
	public String getBusinessAddressCertificateRate() {
		return businessAddressCertificateRate;
	}
	public void setBusinessAddressCertificateRate(
			String businessAddressCertificateRate) {
		this.businessAddressCertificateRate = businessAddressCertificateRate;
	}
	public long getMarriedCount() {
		return marriedCount;
	}
	public void setMarriedCount(long marriedCount) {
		this.marriedCount = marriedCount;
	}
	public String getMarriedRate() {
		return marriedRate;
	}
	public void setMarriedRate(String marriedRate) {
		this.marriedRate = marriedRate;
	}
	public long getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(long childrenCount) {
		this.childrenCount = childrenCount;
	}
	public String getChildrenRate() {
		return childrenRate;
	}
	public void setChildrenRate(String childrenRate) {
		this.childrenRate = childrenRate;
	}
	public long getDegreeCount() {
		return degreeCount;
	}
	public void setDegreeCount(long degreeCount) {
		this.degreeCount = degreeCount;
	}
	public String getDegreeRate() {
		return degreeRate;
	}
	public void setDegreeRate(String degreeRate) {
		this.degreeRate = degreeRate;
	}
	public long getProvidentSocialCount() {
		return providentSocialCount;
	}
	public void setProvidentSocialCount(long providentSocialCount) {
		this.providentSocialCount = providentSocialCount;
	}
	public String getProvidentSocialRate() {
		return providentSocialRate;
	}
	public void setProvidentSocialRate(String providentSocialRate) {
		this.providentSocialRate = providentSocialRate;
	}
	public long getOtherCount() {
		return otherCount;
	}
	public void setOtherCount(long otherCount) {
		this.otherCount = otherCount;
	}
	public String getOtherRate() {
		return otherRate;
	}
	public void setOtherRate(String otherRate) {
		this.otherRate = otherRate;
	}
	public long getInputCompletionCount() {
		return inputCompletionCount;
	}
	public void setInputCompletionCount(long inputCompletionCount) {
		this.inputCompletionCount = inputCompletionCount;
	}
	public String getInputCompletionRate() {
		return inputCompletionRate;
	}
	public void setInputCompletionRate(String inputCompletionRate) {
		this.inputCompletionRate = inputCompletionRate;
	}
	public long getCheckCompletionCount() {
		return checkCompletionCount;
	}
	public void setCheckCompletionCount(long checkCompletionCount) {
		this.checkCompletionCount = checkCompletionCount;
	}
	public String getCheckCompletionRate() {
		return checkCompletionRate;
	}
	public void setCheckCompletionRate(String checkCompletionRate) {
		this.checkCompletionRate = checkCompletionRate;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
	
	
	
	
}
