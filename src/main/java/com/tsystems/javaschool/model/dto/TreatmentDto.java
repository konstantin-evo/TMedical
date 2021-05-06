package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TreatmentDto implements Serializable {
    private PatientDto patientDto;
    String diagnosis;
    LocalDate startDate;
    LocalDate endDate;
    String status;
}
