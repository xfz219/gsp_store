package com.puhui.app.enums;

/**
 * 奖品类型
 */
public enum PrizeType {

    JD_100("100元京东卡"),

    JD_50("50元京东卡"),

    I_QI_YI("爱奇艺会员月卡");

    private final String type;

    PrizeType(String type) {
        this.type = type;
    }

}
