package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.dao.api.UserRepository;
import com.tsystems.javaschool.model.dto.*;
import com.tsystems.javaschool.model.entity.*;
import com.tsystems.javaschool.service.api.TherapyService;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl extends AbstractServiceImpl<Treatment, TreatmentRepository, TreatmentDto, Integer> implements TreatmentService {

    public final UserRepository userRepository;
    public final PatientRepository patientRepository;
    public final TherapyService therapyService;
    public final TherapyRepository therapyRepo;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository dao, ModelMapper mapper, UserRepository userRepository, PatientRepository patientRepository, TherapyService therapyService, TherapyRepository therapyRepo) {
        super(dao, mapper, TreatmentDto.class, Treatment.class);
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.therapyService = therapyService;
        this.therapyRepo = therapyRepo;

    }



    @Override
    public TreatmentDto convertToDTO(Treatment treatment) {

        PatientDto patient = new PatientDto();
        patient.setId(treatment.getPatient().getId());

        UserDto user = new UserDto();
        user.setSurname(treatment.getPatient().getUser().getSurname());
        user.setFirstName(treatment.getPatient().getUser().getFirstName());
        user.setMiddleName(treatment.getPatient().getUser().getMiddleName());
        user.setFullName(new StringBuilder().append(treatment.getPatient().getUser().getSurname()).append(" ").append(treatment.getPatient().getUser().getFirstName().substring(0, 1).toUpperCase()).append(". ").append(treatment.getPatient().getUser().getMiddleName().substring(0, 1).toUpperCase()).append(".").toString());
        user.setDbirth(String.valueOf(treatment.getPatient().getUser().getDbirth()));
        user.setGender(treatment.getPatient().getUser().getGender());
        user.setAddress(treatment.getPatient().getUser().getAddress());
        user.setEmail(treatment.getPatient().getUser().getEmail());

        patient.setUserDto(user);

        String doctor = treatment.getDoctor().getFirstName() + " " + treatment.getDoctor().getSurname();

        TreatmentDto treatmentDto = new TreatmentDto();
        treatmentDto.setPatientDto(patient);
        treatmentDto.setStatus(((treatment.isStatus()) ? "On treatment" : "Is discharged"));
        treatmentDto.setDiagnosis(treatment.getDiagnosis());
        treatmentDto.setStartDate(String.valueOf(treatment.getStartDate()));
        treatmentDto.setEndDate(String.valueOf(treatment.getEndDate()));
        treatmentDto.setDoctor(doctor);
        treatmentDto.setId(treatment.getId());
        //treatmentDto.setTherapies(treatment.getTherapies().stream().map(therapy -> convertToDTO(therapy)).collect(Collectors.toList()));

        return treatmentDto;
    }

//    private TherapyDto convertToDTO(Therapy therapy) {
//        TherapyDto therapyDto = new TherapyDto();
//        therapyDto.setType(therapy.getMedication().getType());
//        therapyDto.setPattern(therapy.getTimePattern());
//        therapyDto.setStartDate(String.valueOf(therapy.getStartDate()));
//        therapyDto.setEndDate(String.valueOf(therapy.getEndDate()));
//        therapyDto.setDose(therapy.getDose());
//        therapyDto.setNumberOfDays(therapy.getNumber());
//
//        return therapyDto;
//    }

    @Override
    public List<TreatmentDto> convertToDTO(List<Treatment> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Treatment convertToEntity(TreatmentDto dto){

        Treatment treatment = new Treatment();

        treatment.setDiagnosis(dto.getDiagnosis());
        treatment.setStatus(true);
        treatment.setStartDate(LocalDate.parse(dto.getStartDate()));
        treatment.setEndDate(LocalDate.parse(dto.getEndDate()));
        treatment.setPatient(patientRepository.findById(dto.getPatientDto().getId()));
        //treatment.setTherapies(con(dto.getTherapies()));
        return treatment;
    }

    @Override
    @Transactional
    public List<TreatmentDto> findByPatientId(int id) {
        Collection<Treatment> list = super.getDao().findTreatmentByPatientId(id);
        return convertToDTO((List<Treatment>) list);
    }

    @Override
    @Transactional
    public void save(TreatmentDto dto, String email) {
        Treatment treatment = convertToEntity(dto);
        treatment.setDoctor(userRepository.findByEmail(email));
        super.getDao().save(treatment);
    }

    @Override
    @Transactional
    public void addTherapy(int id, TherapyDto dto) {
        Therapy therapy = therapyService.convertToEntity(dto);
        Treatment treatment = super.getDao().findById(id);
        therapy.setTreatment(treatment);
       // treatment.setTherapies(Collections.singletonList(therapy));
         therapyRepo.save(therapy);
    }
}
