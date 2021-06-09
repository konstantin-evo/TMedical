package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.model.dto.TherapyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TherapyService {

    List<TherapyDto> findByTreatmentId(int id);
    List<TherapyCaseDto> findCasesByDay(String day);
    TherapyCaseDto findCaseById(int id);
    TherapyDto findById(int id);

    void setStatus(int id, String email, String status);
    void deleteTherapy(int id, String email);
    void sendMessageByDay(String day);

}
