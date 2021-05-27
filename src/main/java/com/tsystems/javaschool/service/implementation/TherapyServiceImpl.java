package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.TherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TherapyServiceImpl implements TherapyService {

    public final MapperService mapper;
    public final TherapyRepository dao;

    @Autowired
    public TherapyServiceImpl (TherapyRepository dao, MapperService mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

}
