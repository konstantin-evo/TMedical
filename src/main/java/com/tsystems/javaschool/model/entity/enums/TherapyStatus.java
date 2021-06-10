package com.tsystems.javaschool.model.entity.enums;

public enum TherapyStatus {
    PLANNED ("Planned"),
    DONE ("Done"),
    CANCELED ("Canceled");

    private final String displayValue;

    TherapyStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
