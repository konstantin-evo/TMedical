package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InsuranceDTO {
    private String insurance_name;
    private String insurance_number;
    private LocalDate start_date;
    private LocalDate end_date;
}
