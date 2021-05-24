package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.model.dto.PatientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<PatientDto> findAll();
    PatientDto findById(int id);
    void save(PatientDto patientDto);
}
