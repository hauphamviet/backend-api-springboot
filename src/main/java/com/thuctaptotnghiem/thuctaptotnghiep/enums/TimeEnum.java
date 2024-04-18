package com.thuctaptotnghiem.thuctaptotnghiep.enums;

public enum TimeEnum {
    SEVEN_TO_EIGHT("7h_8h"),
    EIGHT_TO_NINE("8h_9h"),
    NINE_TO_TEN("9h_10h"),
    TEN_TO_ELEVEN("10h_11h");

    private final String displayName;

    private TimeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
