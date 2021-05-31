package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TherapyCaseRepository;
import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.dao.api.UserRepository;
import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.model.entity.TherapyCase;
import com.tsystems.javaschool.model.entity.enums.TherapyStatus;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.TherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TherapyServiceImpl implements TherapyService {

    public final MapperService mapper;
    public final TherapyRepository dao;
    public final TherapyCaseRepository therapyCaseRepository;
    public final UserRepository userRepository;

    @Autowired
    public TherapyServiceImpl (TherapyRepository dao, TherapyCaseRepository therapyCaseRepository, UserRepository userRepository, MapperService mapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.therapyCaseRepository = therapyCaseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TherapyDto> findByTreatmentId(int id) {
        Collection<Therapy> list = dao.findTherapyByTreatmentId(id);
        return list.stream().map(therapy -> mapper.convertToDto(therapy)).collect(Collectors.toList());
    }

    @Override
    public List<TherapyCaseDto> findCasesByDay(String day) {
        List<TherapyCase> list = therapyCaseRepository.findTherapyCaseByDate(LocalDate.parse(day));
        return list.stream().map(therapyCase -> mapper.converToDto(therapyCase)).collect(Collectors.toList());
    }

    @Override
    public TherapyCaseDto findCaseById(int id) {
        TherapyCase therapyCase = therapyCaseRepository.findById(id);
        return mapper.converToDto(therapyCase);
    }

    @Override
    public void setStatus(int id, String email, String status) {
        TherapyCase therapyCase = therapyCaseRepository.findById(id);
        therapyCase.setStatus(TherapyStatus.valueOf(status));
        therapyCase.setNurse(userRepository.findByEmail(email));
        therapyCaseRepository.update(therapyCase);
    }


}
