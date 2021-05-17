package com.tsystems.javaschool.model.dto;

import com.tsystems.javaschool.model.entity.enums.TreatmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TherapyDto implements Serializable {
    TreatmentType type;
    String pattern;
    String startDate;
    String endDate;
    String dose;
    Integer numberOfDays;
}
