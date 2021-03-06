package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PatientDto implements Serializable {
    private int id;
    private UserDto userDto;
    private String status;
    private List<InsuranceDto> insurances;
    private List<TreatmentDto> treatments;
}
