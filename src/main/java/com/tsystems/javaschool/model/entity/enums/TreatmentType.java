package com.tsystems.javaschool.model.entity.enums;

public enum TreatmentType {
    PROCEDURE("Procedure"),
    MEDICINE("Medicine");

    private final String displayValue;

    private TreatmentType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
