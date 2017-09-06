package com.puhui.app.po;

import com.puhui.app.enums.PrizeChannel;
import com.puhui.app.enums.PrizeType;

/**
 * 奖品数量
 */
public class AppPrizesNumber extends BaseBean {

    /**
     * 奖品渠道
     */
    private PrizeChannel prizeChannel;

    /**
     * 奖品类型
     */
    private PrizeType prizeType;

    /**
     * 奖品数量
     */
    private Integer prizeNumber;

    public PrizeChannel getPrizeChannel() {
        return prizeChannel;
    }

    public void setPrizeChannel(PrizeChannel prizeChannel) {
        this.prizeChannel = prizeChannel;
    }

    public PrizeType getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(PrizeType prizeType) {
        this.prizeType = prizeType;
    }

    public Integer getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(Integer prizeNumber) {
        this.prizeNumber = prizeNumber;
    }
}
