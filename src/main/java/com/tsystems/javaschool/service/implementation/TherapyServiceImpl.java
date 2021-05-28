package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.TherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TherapyServiceImpl implements TherapyService {

    public final MapperService mapper;
    public final TherapyRepository dao;

    @Autowired
    public TherapyServiceImpl (TherapyRepository dao, MapperService mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<TherapyDto> findByTreatmentId(int id) {
        Collection<Therapy> list = dao.findTherapyByTreatmentId(id);
        return list.stream().map(therapy -> mapper.convertToDto(therapy)).collect(Collectors.toList());
    }
}
