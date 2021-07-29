package com.tsystems.javaschool.model.entity.enums;

public enum TherapyStatus {
    PLANNED ("Planned"),
    DONE ("Done"),
    CANCELED ("Canceled"),
    INPROGRESS("In progress"),
    PARTIALLYCOMPLETED("Partially completed (canceled)"),
    DELETED("Deleted");

    private final String displayValue;

    TherapyStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
