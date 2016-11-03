package com.puhui.app.po;

import java.util.Date;

public class AppLendRequest extends BaseBean{
	
	/**
	 * 贷款表
	 * @author wangchao
	 *
	 */
	
	private  static final long serialVersionUID = 2015030510250000000L;
	
	/**
	 * 客户id
	 * @author wangchao
	 */
	private Long appCustomerId;
	
	/**
	 * 客户经理员工编号
	 * @author wangchao
	 */
	private Long customerServiceManagerNumber;
	
	/**
	 * 审核金额(元)
	 * @author gaozhenbao
	 */
	private Double approvalAmount;
	
	/**
	 * 审批还款期数
	 */
	private int approvalLoanPeriod;
	/**
	 * 审批月还款额
	 */
	private Double approvalMonthlyRepay;
	
	/**
	 * 进件状态
	 * @author gaozhenbao
	 * */
	private Integer state;
	
	/**
	 * 进件审核状态
	 * @author gaozhenbao
	 * */
	private Integer auditState;

	/**
	 * 首次进入时间
	 * @author gaozhenbao
	 * */
	private Date firstStartTime;
	
	/**
	 * 录入开始时间
	 * @author gaozhenbao
	 * */
	private Date startTime;
	
	/**
	 * 录入结束时间
	 * @author gaozhenbao
	 * */
	private Date endTime;
	
	/**
	 * 记录创建时间
	 * @author wangchao
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 * @author wangchao
	 */
	private Date updateTime;
	
	/**
	 * 放款金额
	 */
	private Double loanAmount;
	
	private Long dataCenterId;
	
	
	public Long getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Long dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public Long getAppCustomerId() {
		return appCustomerId;
	}

	public void setAppCustomerId(Long appCustomerId) {
		this.appCustomerId = appCustomerId;
	}

	public Long getCustomerServiceManagerNumber() {
		return customerServiceManagerNumber;
	}

	public void setCustomerServiceManagerNumber(Long customerServiceManagerNumber) {
		this.customerServiceManagerNumber = customerServiceManagerNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getFirstStartTime() {
		return firstStartTime;
	}

	public void setFirstStartTime(Date firstStartTime) {
		this.firstStartTime = firstStartTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getAuditState() {
		return auditState;
	}

	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}

	public int getApprovalLoanPeriod() {
		return approvalLoanPeriod;
	}

	public void setApprovalLoanPeriod(int approvalLoanPeriod) {
		this.approvalLoanPeriod = approvalLoanPeriod;
	}

	public Double getApprovalMonthlyRepay() {
		return approvalMonthlyRepay;
	}

	public void setApprovalMonthlyRepay(Double approvalMonthlyRepay) {
		this.approvalMonthlyRepay = approvalMonthlyRepay;
	}

	public Double getApprovalAmount() {
		return approvalAmount;
	}

	public void setApprovalAmount(Double approvalAmount) {
		this.approvalAmount = approvalAmount;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
