package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TherapyCaseDto {
    int id;
    String patient;
    String medical;
    String doctor;
    String date;
    String time;
    String status;
}
