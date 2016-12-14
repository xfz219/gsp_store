package com.puhui.app.vo;

/**
 * 推送消息
 * 
 * @author gaozhenbao
 */
public class AppPushMessageVo {

    /**
     * 推送类型
     * 
     * @author gaozhenbao
     */
    private int pushType;

    /**
     * 推送模板
     * 
     * @author gaozhenbao
     */
    private int pushModel;

    /**
     * 进件ID
     * 
     * @author gaozhenbao
     */
    private Long appLendRequestId;

    /**
     * 材料类型
     * 
     * @author gaozhenbao
     */
    private int type;

    /**
     * 原因
     * 
     * @author gaozhenbao
     */
    private String message;
    /**
     * 其它原因
     */
    private String otherMessage;
    
    /**
     * 销售id
     */
	private Integer sellerNumber;

    public Integer getSellerNumber() {
		return sellerNumber;
	}

	public void setSellerNumber(Integer sellerNumber) {
		this.sellerNumber = sellerNumber;
	}

	public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public int getPushModel() {
        return pushModel;
    }

    public void setPushModel(int pushModel) {
        this.pushModel = pushModel;
    }

    public Long getAppLendRequestId() {
        return appLendRequestId;
    }

    public void setAppLendRequestId(Long appLendRequestId) {
        this.appLendRequestId = appLendRequestId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtherMessage() {
        return otherMessage;
    }

    public void setOtherMessage(String otherMessage) {
        this.otherMessage = otherMessage;
    }

}