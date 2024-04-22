package com.thuctaptotnghiem.thuctaptotnghiep.enums;

import java.time.LocalDateTime;

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

    public TimeEnum convertToBookingTimeEnum(LocalDateTime time) {
        int hour = time.getHour();
        if (hour >= 7 && hour < 8) {
            return TimeEnum.SEVEN_TO_EIGHT;
        } else if (hour >= 8 && hour < 9) {
            return TimeEnum.EIGHT_TO_NINE;
        } else if (hour >= 9 && hour < 10) {
            return TimeEnum.NINE_TO_TEN;
        } else if (hour >= 10 && hour < 11) {
            return TimeEnum.TEN_TO_ELEVEN;
        } else {
            throw new IllegalArgumentException("Invalid time: " + time);
        }
    }

}
