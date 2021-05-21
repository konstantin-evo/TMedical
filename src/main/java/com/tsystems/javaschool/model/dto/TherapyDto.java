package com.tsystems.javaschool.model.dto;

import com.tsystems.javaschool.model.entity.enums.TreatmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TherapyDto implements Serializable {
    private String medication;
    private String pattern;
    private String startDate;
    private String endDate;
    private String dose;
    private Integer numberOfDays;
}
