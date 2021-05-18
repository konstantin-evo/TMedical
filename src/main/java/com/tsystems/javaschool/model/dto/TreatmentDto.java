package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class TreatmentDto implements Serializable {

    private Integer id;
    private PatientDto patientDto;
    private String doctor;
    private String diagnosis;
    private String startDate;
    private String endDate;
    private String status;

    private List<TherapyDto> therapies;

}
