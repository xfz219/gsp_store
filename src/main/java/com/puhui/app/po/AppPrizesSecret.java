package com.puhui.app.po;

import com.puhui.app.enums.PrizeType;

import java.util.Date;

/**
 * 奖品秘密
 */
public class AppPrizesSecret extends BaseBean {

    /**
     * 奖品类型
     */
    private PrizeType prizeType;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 使用（是、否）
     */
    private Boolean use;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public PrizeType getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(PrizeType prizeType) {
        this.prizeType = prizeType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }
}
