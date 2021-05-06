package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PatientDto implements Serializable {
    private int id;
    private UserDto userDto;
    private List<InsuranceDto> insurances;
}
