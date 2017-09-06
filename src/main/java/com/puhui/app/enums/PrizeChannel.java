package com.puhui.app.enums;

/**
 * 奖品渠道
 */
public enum PrizeChannel {

    QUESTIONNAIRE_CHANNEL("调查问卷渠道（第一次）");

    private final String type;

    PrizeChannel(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
