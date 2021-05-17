package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.service.api.TherapyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TherapyServiceImpl extends AbstractServiceImpl<Therapy, TherapyRepository, TherapyDto, Integer> implements TherapyService {

    @Autowired
    public TherapyServiceImpl(TherapyRepository dao, ModelMapper mapper) {
        super(dao, mapper, TherapyDto.class, Therapy.class);
    }
}
