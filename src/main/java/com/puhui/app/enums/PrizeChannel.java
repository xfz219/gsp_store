package com.puhui.app.enums;

/**
 * 奖品渠道
 */
public enum PrizeChannel {

    QUESTIONNAIRE_CHANNEL_V1("调查问卷V1");

    private final String type;

    PrizeChannel(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
