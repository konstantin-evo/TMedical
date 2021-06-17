package com.tsystems.javaschool.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tsystems.javaschool.dao.api.MedicamentationRepository;
import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.model.dto.*;
import com.tsystems.javaschool.model.entity.*;
import com.tsystems.javaschool.model.entity.enums.Role;
import com.tsystems.javaschool.model.entity.enums.TherapyStatus;
import com.tsystems.javaschool.service.api.MapperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
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

        boolean condition = patientEntity.getTreatments().stream().anyMatch(Treatment::isStatus);

        if (condition) {
            patientDto.setStatus("On treatment");
        } else {
            patientDto.setStatus("Not on treatment");
        }

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

        if (!dto.getUserDto().getMiddleName().equals("")) {
            user.setMiddleName(dto.getUserDto().getMiddleName());
        }

        user.setGender(dto.getUserDto().getGender());
        user.setAddress(dto.getUserDto().getAddress());
        user.setEmail(dto.getUserDto().getEmail());
        user.setRole(Role.valueOf("OTHER"));
        user.setDbirth(LocalDate.parse(dto.getUserDto().getDbirth()));

        patient.setUser(user);

        return patient;
    }

    @Override
    public UserDto convertToDto(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setSurname(userEntity.getSurname());
        dto.setFirstName(userEntity.getFirstName());

        if (userEntity.getMiddleName() != null) {
            dto.setMiddleName(userEntity.getMiddleName());
        } else {
            dto.setMiddleName(" ");
        }

        dto.setDbirth(String.valueOf(userEntity.getDbirth()));
        dto.setGender(userEntity.getGender());
        dto.setAddress(userEntity.getAddress());
        dto.setEmail(userEntity.getEmail());

        if (userEntity.getMiddleName() != null) {
            dto.setFullName(new StringBuilder().append(userEntity.getSurname()).append(" ").append(userEntity.getFirstName().substring(0, 1).toUpperCase()).append(". ").append(userEntity.getMiddleName().substring(0, 1).toUpperCase()).append(".").toString());
        } else {
            dto.setFullName(new StringBuilder().append(userEntity.getSurname()).append(" ").append(userEntity.getFirstName().substring(0, 1).toUpperCase()).append(". ").toString());
        }

        return dto;
    }

    @Override
    public String converToJson(List<TherapyCaseDto> dto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(dto);
    }

    @Override
    public Therapy convertToEntity(TherapyDto dto){

        Therapy therapy = new Therapy();
        LocalDate startDate = LocalDate.parse(dto.getStartDate());

        therapy.setDose(dto.getDose());
        therapy.setTimePattern(dto.getPattern());
        therapy.setStartDate(startDate);
        therapy.setNumber(dto.getNumberOfDays());
        therapy.setStatus(TherapyStatus.valueOf("PLANNED"));
        therapy.setMedication(medicamentationRepository.findMedicationByName(dto.getMedicationName()));

        return therapy;
    }

    @Override
    public TherapyDto convertToDto(Therapy therapy) {
        TherapyDto therapyDto = new TherapyDto();
        therapyDto.setId(therapy.getId());
        therapyDto.setMedicationName(String.valueOf(therapy.getMedication().getName()));
        therapyDto.setPattern(therapy.getTimePattern().toLowerCase());
        therapyDto.setStartDate(String.valueOf(therapy.getStartDate()));
        therapyDto.setStatus(String.valueOf(therapy.getStatus().getDisplayValue()));

        if (!therapy.getDose().equals("")) {
            String dose = " (" + therapy.getDose() + ")";
            therapyDto.setDose(dose);} else {
            therapyDto.setDose("");
        }

        therapyDto.setNumberOfDays(therapy.getNumber());
        therapyDto.setTherapyCaseDtos(therapy.getTherapyCases().stream()
                .sorted(Comparator.comparing(TherapyCase::getDate))
                .map(this::converToDto).collect(Collectors.toList()));

        CaseWrapper caseWrapper = new CaseWrapper();
        caseWrapper.setCases(therapy.getTherapyCases().stream().map(this::converToDto).collect(Collectors.toList()));
        therapyDto.setCaseWrapper(caseWrapper);

        return therapyDto;
    }

    @Override
    public TreatmentDto convertToDto(Treatment treatment) {
        String patietnName;
        String doctor = treatment.getDoctor().getFirstName() + " " + treatment.getDoctor().getSurname();

        if(treatment.getPatient().getUser().getMiddleName() != null) {
            patietnName = treatment.getPatient().getUser().getSurname() + " " + treatment.getPatient().getUser().getFirstName().substring(0, 1).toUpperCase() + ". " + treatment.getPatient().getUser().getMiddleName().substring(0, 1).toUpperCase() + ".";
        } else {
            patietnName = treatment.getPatient().getUser().getSurname() + " " + treatment.getPatient().getUser().getFirstName().substring(0, 1).toUpperCase() + ". ";
        }

        TreatmentDto treatmentDto = new TreatmentDto();
        treatmentDto.setPatientName(patietnName);
        treatmentDto.setPatientDbirth(String.valueOf(treatment.getPatient().getUser().getDbirth()));
        treatmentDto.setStatus(((treatment.isStatus()) ? "On treatment" : "Is discharged"));
        treatmentDto.setDiagnosis(treatment.getDiagnosis());
        treatmentDto.setStartDate(String.valueOf(treatment.getStartDate()));

        if (treatment.getEndDate() == null) {
            treatmentDto.setEndDate("");
        } else {
            treatmentDto.setEndDate(String.valueOf(treatment.getEndDate()));
        }

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

        if (dto.getEndDate() != null) {
            treatment.setEndDate(LocalDate.parse(dto.getEndDate()));
        } else {
            treatment.setEndDate(null);
        }

        return treatment;
    }

    @Override
    public TherapyCase convertToEntity(TherapyCaseDto dto){
        TherapyCase therapyCase = new TherapyCase();
        therapyCase.setStatus(TherapyStatus.valueOf("PLANNED"));
        therapyCase.setDate(LocalDate.parse(dto.getDate()));
        therapyCase.setTime(LocalTime.parse(dto.getTime()));
        return therapyCase;
    }

    @Override
    public TherapyCaseDto converToDto(TherapyCase therapyCase){
        TherapyCaseDto dto = new TherapyCaseDto();
        dto.setDate(String.valueOf(therapyCase.getDate()));
        dto.setStatus(String.valueOf(therapyCase.getStatus().getDisplayValue()));
        dto.setTime(String.valueOf(therapyCase.getTime()));

        String patient = therapyCase.getTherapy().getTreatment().getPatient().getUser().getSurname() + " " + therapyCase.getTherapy().getTreatment().getPatient().getUser().getFirstName().charAt(0) + ".";
        String doctor = therapyCase.getTherapy().getTreatment().getDoctor().getSurname() + " " + therapyCase.getTherapy().getTreatment().getDoctor().getFirstName().charAt(0) + ".";
        dto.setPatient(patient);
        dto.setDoctor(doctor);
        dto.setMedical(therapyCase.getTherapy().getMedication().getName());
        dto.setId(therapyCase.getId());

        return dto;
    }

}
