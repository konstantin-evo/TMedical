package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.model.entity.Patient;
import com.tsystems.javaschool.service.api.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends AbstractServiceImpl<Patient, PatientRepository, PatientDto, Integer> implements PatientService {

    @Autowired
    public PatientServiceImpl(PatientRepository dao, ModelMapper mapper) {
        super(dao, mapper, PatientDto.class, Patient.class);
    }

}
