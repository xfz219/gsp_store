package com.puhui.app.po;

import java.io.Serializable;

/**
 * @comment app耗时分析表返回数据实体类
 * @author liwang
 * @time 2015年7月21日 下午4:30:11
 */
public class ConsumingAnalysisReport implements Serializable{
	
	private static final long serialVersionUID = -6573684561553641224L;

	private String customerName;//客户名称
	private String salesName;//销售名称
	private String requestId;//进件号
	private String dataTotalTime;//录入资料总耗时
	private String identityTime;//身份信息耗时
	private String occupationTime;//职业信息耗时
	private String contactTime;//联系人信息耗时
	private String creditReportTime;//征信报告信息耗时
	private String taobaoTime;//淘宝信息耗时
	private String communicationTime;//运营商信息耗时
	private String payFlowTime;//工资流水信息耗时
	private String idPhotoTime;//身份证照片耗时
	private String residenceCertificateTime;//居住证明信息耗时
	private String workCertificateTime;//工作证明信息耗时
	private String estateCertificateTime;//房产证明信息耗时
	private String operatorCertificateTime;//经营证明信息耗时
	private String businessAddressCertificateTime;//经营地址证明信息耗时
	private String marriedTime;//已婚证明信息耗时
	private String childrenTime;//子女证明信息耗时
	private String degreeTime;//学历证明信息耗时
	private String providentSocialTime;//社保公积金信息耗时
	private String otherTime;//其他证明信息耗时
	private int productUpdateCount;//产品更换次数
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getDataTotalTime() {
		return dataTotalTime;
	}
	public void setDataTotalTime(String dataTotalTime) {
		this.dataTotalTime = dataTotalTime;
	}
	public String getIdentityTime() {
		return identityTime;
	}
	public void setIdentityTime(String identityTime) {
		this.identityTime = identityTime;
	}
	public String getOccupationTime() {
		return occupationTime;
	}
	public void setOccupationTime(String occupationTime) {
		this.occupationTime = occupationTime;
	}
	public String getContactTime() {
		return contactTime;
	}
	public void setContactTime(String contactTime) {
		this.contactTime = contactTime;
	}
	public String getCreditReportTime() {
		return creditReportTime;
	}
	public void setCreditReportTime(String creditReportTime) {
		this.creditReportTime = creditReportTime;
	}
	public String getTaobaoTime() {
		return taobaoTime;
	}
	public void setTaobaoTime(String taobaoTime) {
		this.taobaoTime = taobaoTime;
	}
	public String getCommunicationTime() {
		return communicationTime;
	}
	public void setCommunicationTime(String communicationTime) {
		this.communicationTime = communicationTime;
	}
	public String getPayFlowTime() {
		return payFlowTime;
	}
	public void setPayFlowTime(String payFlowTime) {
		this.payFlowTime = payFlowTime;
	}
	public String getIdPhotoTime() {
		return idPhotoTime;
	}
	public void setIdPhotoTime(String idPhotoTime) {
		this.idPhotoTime = idPhotoTime;
	}
	public String getResidenceCertificateTime() {
		return residenceCertificateTime;
	}
	public void setResidenceCertificateTime(String residenceCertificateTime) {
		this.residenceCertificateTime = residenceCertificateTime;
	}
	public String getWorkCertificateTime() {
		return workCertificateTime;
	}
	public void setWorkCertificateTime(String workCertificateTime) {
		this.workCertificateTime = workCertificateTime;
	}
	public String getEstateCertificateTime() {
		return estateCertificateTime;
	}
	public void setEstateCertificateTime(String estateCertificateTime) {
		this.estateCertificateTime = estateCertificateTime;
	}
	public String getOperatorCertificateTime() {
		return operatorCertificateTime;
	}
	public void setOperatorCertificateTime(String operatorCertificateTime) {
		this.operatorCertificateTime = operatorCertificateTime;
	}
	public String getBusinessAddressCertificateTime() {
		return businessAddressCertificateTime;
	}
	public void setBusinessAddressCertificateTime(
			String businessAddressCertificateTime) {
		this.businessAddressCertificateTime = businessAddressCertificateTime;
	}
	public String getMarriedTime() {
		return marriedTime;
	}
	public void setMarriedTime(String marriedTime) {
		this.marriedTime = marriedTime;
	}
	public String getChildrenTime() {
		return childrenTime;
	}
	public void setChildrenTime(String childrenTime) {
		this.childrenTime = childrenTime;
	}
	public String getDegreeTime() {
		return degreeTime;
	}
	public void setDegreeTime(String degreeTime) {
		this.degreeTime = degreeTime;
	}
	public String getProvidentSocialTime() {
		return providentSocialTime;
	}
	public void setProvidentSocialTime(String providentSocialTime) {
		this.providentSocialTime = providentSocialTime;
	}
	public String getOtherTime() {
		return otherTime;
	}
	public void setOtherTime(String otherTime) {
		this.otherTime = otherTime;
	}
	public int getProductUpdateCount() {
		return productUpdateCount;
	}
	public void setProductUpdateCount(int productUpdateCount) {
		this.productUpdateCount = productUpdateCount;
	}
	
	
	
}
