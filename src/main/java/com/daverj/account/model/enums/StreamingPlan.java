package com.daverj.account.model.enums;

public enum StreamingPlan {

    BASIC(1),
    STANDARD(2),
    PREMIUM(3);

    private int code;

    private StreamingPlan(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StreamingPlan valueOf(int code) {

        for (StreamingPlan value : StreamingPlan.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid StreamingPlan code");

    }

}