package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TherapyService {

    List<TherapyDto> findByTreatmentId(int id);
    List<TherapyCaseDto> findCasesByDay(String day);
    TherapyCaseDto findCaseById(int id);
    TherapyDto findById(int id);
    TreatmentDto findTreatmentByCaseId(int id);

    void setCaseStatus(int id, String email, String status);
    void changeTherapyStatus(int id);
    void deleteTherapy(int id, String email);

}
