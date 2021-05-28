package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.model.dto.TherapyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TherapyService {

    List<TherapyDto> findByTreatmentId(int id);

}
