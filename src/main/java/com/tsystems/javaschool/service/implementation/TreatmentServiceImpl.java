package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.controller.exception.ExceptionTreatmentNotFound;
import com.tsystems.javaschool.dao.api.*;
import com.tsystems.javaschool.model.dto.*;
import com.tsystems.javaschool.model.entity.*;
import com.tsystems.javaschool.model.entity.enums.TherapyStatus;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.TherapyService;
import com.tsystems.javaschool.service.api.TreatmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TreatmentServiceImpl implements TreatmentService {

    public final TreatmentRepository dao;
    public final UserRepository userRepository;
    public final PatientRepository patientRepository;
    public final TherapyService therapyService;
    public final TherapyRepository therapyRepository;
    public final TherapyCaseRepository therapyCaseRepository;
    public final MapperService mapper;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository dao, UserRepository userRepository, PatientRepository patientRepository, TherapyService therapyService, TherapyRepository therapyRepository, TherapyCaseRepository therapyCaseRepository, MapperService mapper) {
        this.dao = dao;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.therapyService = therapyService;
        this.therapyRepository = therapyRepository;
        this.therapyCaseRepository = therapyCaseRepository;
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
    public TreatmentDto findById(int id) throws ExceptionTreatmentNotFound {
        Treatment found = null;
        try {
             found = dao.findById(id);
        } catch (Exception e){
            log.info("No contact found with id: {}", id);
            throw new ExceptionTreatmentNotFound("No contact found with id:" + id);
        }

        return mapper.convertToDto(found);
    }

    @Override
    @Transactional
    public TreatmentDto findByTherapyId(int id) throws ExceptionTreatmentNotFound {
        Therapy therapy = therapyRepository.findById(id);
        return findById(therapy.getTreatment().getId());
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

        List<LocalDateTime> times = createTherapyDays(dto.getWrapper().getDays(), dto.getNumberOfDays());
        List<TherapyCase> therapyCases = new ArrayList<>();

        for(LocalDateTime time : times) {
           TherapyCase therapyCase = new TherapyCase();
           therapyCase.setStatus(TherapyStatus.valueOf("PLANNED"));
           therapyCase.setTime(time.toLocalTime());
           therapyCase.setDate(time.toLocalDate());
           therapyCase.setTherapy(therapy);
           therapyCases.add(therapyCase);
        }
        therapy.setTherapyCases(therapyCases);
        therapy.setTreatment(treatment);
        therapyRepository.save(therapy);
        dao.update(treatment);
    }

    public List<LocalDateTime> createTherapyDays(List<TherapyDaysDto> therapyDays, int count) {
        List<LocalDateTime> list = new ArrayList<>();
        LocalDate currentDay = LocalDate.now();
        DayOfWeek currentDayOfWeek = currentDay.getDayOfWeek();
        int currentCount = 0;

        therapyDays = therapyDays.stream().filter(therapyDaysDto -> !therapyDaysDto.getTime().equals("")).collect(Collectors.toList());

        while (currentCount < count) {
            for (TherapyDaysDto therapyDaysDto : therapyDays) {
                String nameOfDay = therapyDaysDto.getDay().getDisplayValue();

                if ((currentCount < count) && (currentDayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).equalsIgnoreCase(nameOfDay))) {
                    list.add(currentDay.atTime(LocalTime.parse(therapyDaysDto.getTime())));
                    currentCount++;
                }
            }
            currentDay = currentDay.plusDays(1);
            currentDayOfWeek = currentDay.getDayOfWeek();
        }
        return list;
    }
}
