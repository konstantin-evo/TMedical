package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.controller.exception.ExceptionTreatmentNotFound;
import com.tsystems.javaschool.model.dto.TherapyDaysDto;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TreatmentService {

    List<TreatmentDto> findByPatientId(int id);
    TreatmentDto findById(int id) throws ExceptionTreatmentNotFound;
    TreatmentDto findByTherapyId(int id) throws ExceptionTreatmentNotFound;
    List<TreatmentDto> findAll();
    List<LocalDateTime> createTherapyDays(List<TherapyDaysDto> days, int count);

    void save(TreatmentDto dto, String email, int id);
    void addTherapy(int id, TherapyDto dto);
}
