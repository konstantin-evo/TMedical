package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.dao.api.UserRepository;
import com.tsystems.javaschool.model.dto.*;
import com.tsystems.javaschool.model.entity.*;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.TherapyService;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    public final TreatmentRepository dao;
    public final UserRepository userRepository;
    public final PatientRepository patientRepository;
    public final TherapyService therapyService;
    public final TherapyRepository therapyRepository;
    public final MapperService mapper;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository dao, UserRepository userRepository, PatientRepository patientRepository, TherapyService therapyService, TherapyRepository therapyRepository, MapperService mapper) {
        this.dao = dao;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.therapyService = therapyService;
        this.therapyRepository = therapyRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<TreatmentDto> findByPatientId(int id) {
        Collection<Treatment> list = dao.findTreatmentByPatientId(id);
        return list.stream().map(treatment -> mapper.convertToDto(treatment)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TreatmentDto findById(int id) {
        return mapper.convertToDto(dao.findById(id));
    }

    @Override
    @Transactional
    public List<TreatmentDto> findAll() {
        return dao.findAll().stream().map(treatment -> mapper.convertToDto(treatment)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(TreatmentDto dto, String email, int id) {
        Treatment treatment = mapper.convertToEntity(dto);
        treatment.setDoctor(userRepository.findByEmail(email));
        treatment.setPatient(patientRepository.findById(id));
        dao.save(treatment);
    }

    @Transactional
    public void addTherapy(int id, TherapyDto dto) {
        Therapy therapy = mapper.convertToEntity(dto);
        Treatment treatment = dao.findById(id);
        therapy.setTreatment(treatment);
        therapyRepository.save(therapy);
    }
}
