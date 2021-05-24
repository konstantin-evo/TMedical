package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TreatmentService {

    List<TreatmentDto> findByPatientId(int id);
    TreatmentDto findById(int id);
    List<TreatmentDto> findAll();

    void save(TreatmentDto dto, String email, int id);
    void addTherapy(int id, TherapyDto dto);
}
