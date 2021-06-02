package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InsuranceDto implements Serializable {
    private String insuranceName;
    private String insuranceNumber;
    private LocalDate startDate;
    private LocalDate endDate;
}
