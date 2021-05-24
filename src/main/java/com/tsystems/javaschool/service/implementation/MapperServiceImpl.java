package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.MedicamentationRepository;
import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.model.dto.*;
import com.tsystems.javaschool.model.entity.*;
import com.tsystems.javaschool.model.entity.enums.Role;
import com.tsystems.javaschool.service.api.MapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MapperServiceImpl implements MapperService {

    public final MedicamentationRepository medicamentationRepository;
    public final TreatmentRepository treatmentRepository;
    public final PatientRepository patientRepository;

    public MapperServiceImpl(MedicamentationRepository medicamentationRepository, TreatmentRepository treatmentRepository, PatientRepository patientRepository) {
        this.medicamentationRepository = medicamentationRepository;
        this.treatmentRepository = treatmentRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDto convertToDto(Patient patientEntity) {
        List<InsuranceDto> insuranceDtos = patientEntity.getInsurances().stream().map(this::convertToDto).collect(Collectors.toList());
        List<TreatmentDto> treatmentDtos = patientEntity.getTreatments().stream().map(this::convertToDto).collect(Collectors.toList());

        PatientDto patientDto = new PatientDto();
        patientDto.setId(patientEntity.getId());
        patientDto.setUserDto(convertToDto(patientEntity.getUser()));

        patientDto.setInsurances(insuranceDtos);
        patientDto.setTreatments(treatmentDtos);

        return patientDto;
    }

    @Override
    public InsuranceDto convertToDto(Insurance insurance) {
        InsuranceDto insuranceDto = new InsuranceDto();
        insuranceDto.setInsuranceName(insurance.getName());
        insuranceDto.setInsuranceNumber(insurance.getNumber());
        insuranceDto.setStartDate(insurance.getStartDate());
        insuranceDto.setEndDate(insurance.getEndDate());
        return insuranceDto;
    }

    @Override
    public Patient convertToEntity(PatientDto dto) {

        Patient patient = new Patient();

        UserEntity user = new UserEntity();
        user.setFirstName(dto.getUserDto().getFirstName());
        user.setSurname(dto.getUserDto().getSurname());
        user.setMiddleName(dto.getUserDto().getMiddleName());
        user.setGender(dto.getUserDto().getGender());
        user.setAddress(dto.getUserDto().getAddress());
        user.setEmail(dto.getUserDto().getEmail());
        user.setRole(Role.valueOf("OTHER"));
        user.setDbirth(LocalDate.parse(dto.getUserDto().getDbirth()));

        patient.setUser(user);

        return patient;
    }

    @Override
    @Transactional
    public UserDto convertToDto(UserEntity userEntity) {
        UserDto user = new UserDto();
        user.setSurname(userEntity.getSurname());
        user.setFirstName(userEntity.getFirstName());
        user.setMiddleName(userEntity.getMiddleName());
        user.setDbirth(String.valueOf(userEntity.getDbirth()));
        user.setGender(userEntity.getGender());
        user.setAddress(userEntity.getAddress());
        user.setEmail(userEntity.getEmail());
        user.setFullName(new StringBuilder().append(userEntity.getSurname()).append(" ").append(userEntity.getFirstName().substring(0, 1).toUpperCase()).append(". ").append(userEntity.getMiddleName().substring(0, 1).toUpperCase()).append(".").toString());

        return user;
    }

    @Override
    public Therapy convertToEntity(TherapyDto dto){

        Therapy therapy = new Therapy();

        therapy.setDose(dto.getDose());
        therapy.setTimePattern(dto.getPattern());
        therapy.setStartDate(LocalDate.parse(dto.getStartDate()));
        therapy.setEndDate(LocalDate.parse(dto.getEndDate()));
        therapy.setNumber(dto.getNumberOfDays());
        therapy.setMedication(medicamentationRepository.findMedicationByName(dto.getMedication()));
        return therapy;
    }

    @Override
    public TherapyDto convertToDto(Therapy therapy) {
        TherapyDto therapyDto = new TherapyDto();
        therapyDto.setMedication(String.valueOf(therapy.getMedication()));
        therapyDto.setPattern(therapy.getTimePattern());
        therapyDto.setStartDate(String.valueOf(therapy.getStartDate()));
        therapyDto.setEndDate(String.valueOf(therapy.getEndDate()));
        therapyDto.setDose(therapy.getDose());
        therapyDto.setNumberOfDays(therapy.getNumber());

        return therapyDto;
    }

    @Override
    @Transactional
    public TreatmentDto convertToDto(Treatment treatment) {

        String doctor = treatment.getDoctor().getFirstName() + " " + treatment.getDoctor().getSurname();
        String patietnName = treatment.getPatient().getUser().getSurname() + " " + treatment.getPatient().getUser().getFirstName().substring(0, 1).toUpperCase() + ". " + treatment.getPatient().getUser().getMiddleName().substring(0, 1).toUpperCase() + ".";

        TreatmentDto treatmentDto = new TreatmentDto();
        treatmentDto.setPatientName(patietnName);
        treatmentDto.setPatientDbirth(String.valueOf(treatment.getPatient().getUser().getDbirth()));
        treatmentDto.setStatus(((treatment.isStatus()) ? "On treatment" : "Is discharged"));
        treatmentDto.setDiagnosis(treatment.getDiagnosis());
        treatmentDto.setStartDate(String.valueOf(treatment.getStartDate()));
        treatmentDto.setEndDate(String.valueOf(treatment.getEndDate()));
        treatmentDto.setDoctor(doctor);
        treatmentDto.setId(treatment.getId());

        return treatmentDto;
    }

    @Override
    public Treatment convertToEntity(TreatmentDto dto){

        Treatment treatment = new Treatment();

        treatment.setDiagnosis(dto.getDiagnosis());
        treatment.setStatus(true);
        treatment.setStartDate(LocalDate.parse(dto.getStartDate()));
        treatment.setEndDate(LocalDate.parse(dto.getEndDate()));
        return treatment;
    }

}
