package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository dao;
    private final MapperService mapper;

    @Autowired
    public PatientServiceImpl(PatientRepository dao, MapperService mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<PatientDto> findAll() {
        return dao.findAll().stream().map(patient -> mapper.convertToDto(patient)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PatientDto findById(int id) {
        return mapper.convertToDto(dao.findById(id));
    }

    @Override
    public void save(PatientDto patientDto) {
        dao.save(mapper.convertToEntity(patientDto));
    }
}
