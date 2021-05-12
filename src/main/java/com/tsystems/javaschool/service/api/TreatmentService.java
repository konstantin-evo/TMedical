package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.model.entity.Treatment;

import java.util.List;

public interface TreatmentService extends AbstractService<Treatment, TreatmentDto, Integer> {

    List<TreatmentDto> findByPatientId(int id);

}
