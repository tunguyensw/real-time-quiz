package com.elsaspeak.quiz.enums;

public enum PlayStatus {
    PENDING, COMPLETED_ONE_TIME, COMPLETED_OUT_TIME, ABORT;

    public static PlayStatus get(String status) {
        return valueOf(status);
    }
}
