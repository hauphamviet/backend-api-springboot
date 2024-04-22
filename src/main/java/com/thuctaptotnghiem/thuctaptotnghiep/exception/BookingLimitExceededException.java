package com.thuctaptotnghiem.thuctaptotnghiep.exception;

public class BookingLimitExceededException extends RuntimeException {
    public BookingLimitExceededException() {
        super("Booking limit exceeded.");
    }

    public BookingLimitExceededException(String message) {
        super(message);
    }

    public BookingLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}