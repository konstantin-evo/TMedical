package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.model.entity.Treatment;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TreatmentServiceImpl extends AbstractServiceImpl<Treatment, TreatmentRepository, TreatmentDto, Integer> implements TreatmentService {

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository dao, ModelMapper mapper) {
        super(dao, mapper, TreatmentDto.class, Treatment.class);
    }
}
