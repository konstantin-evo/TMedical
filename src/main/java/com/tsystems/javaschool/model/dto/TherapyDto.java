package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TherapyDto implements Serializable {
    private String medication;
    private String pattern;
    private String startDate;
    private String dose;
    private Integer numberOfDays;
    private List<TherapyCaseDto> therapyCaseDtos;
    private DaysWrapper wrapper;
}
