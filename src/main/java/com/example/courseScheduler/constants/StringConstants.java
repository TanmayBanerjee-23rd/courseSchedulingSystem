package com.example.courseScheduler.constants;

public enum StringConstants {
    INPUT_DATA_ERROR("INPUT_DATA_ERROR"),
    COURSE_FULL_ERROR("COURSE_FULL_ERROR"),
    COURSE_REGISTRATION_ACCEPTED("ACCEPTED"),
    CANCEL_ACCEPTED("CANCEL_ACCEPTED"),
    CANCEL_REJECTED("CANCEL_REJECTED"),
    COURSE_ALLOTMENT_CONFIRMED("CONFIRMED"),
    COURSE_ALLOTMENT_NOT_INITIATED("NOT_INITIATED");

    StringConstants(String string) {
        this.string = string;
    }

    private final String string;

    public String getValue() {
        return string;
    }
}
