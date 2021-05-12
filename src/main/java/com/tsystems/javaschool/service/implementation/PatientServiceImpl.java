package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.model.dto.InsuranceDto;
import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.model.dto.UserDto;
import com.tsystems.javaschool.model.entity.Insurance;
import com.tsystems.javaschool.model.entity.Patient;
import com.tsystems.javaschool.model.entity.Treatment;
import com.tsystems.javaschool.model.entity.UserEntity;
import com.tsystems.javaschool.model.entity.enums.Role;
import com.tsystems.javaschool.service.api.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl extends AbstractServiceImpl<Patient, PatientRepository, PatientDto, Integer> implements PatientService {

    @Autowired
    public PatientServiceImpl(PatientRepository dao, ModelMapper mapper) {
        super(dao, mapper, PatientDto.class, Patient.class);
    }

    @Override
    public PatientDto convertToDTO(Patient patientEntity) {
        int id = patientEntity.getId();

        UserDto user = new UserDto();
        user.setSurname(patientEntity.getUser().getSurname());
        user.setFirstName(patientEntity.getUser().getFirstName());
        user.setMiddleName(patientEntity.getUser().getMiddleName());
        user.setDbirth(String.valueOf(patientEntity.getUser().getDbirth()));
        user.setGender(patientEntity.getUser().getGender());
        user.setAddress(patientEntity.getUser().getAddress());
        user.setEmail(patientEntity.getUser().getEmail());
        user.setFullName(new StringBuilder().append(patientEntity.getUser().getSurname()).append(" ").append(patientEntity.getUser().getFirstName().substring(0, 1).toUpperCase()).append(". ").append(patientEntity.getUser().getMiddleName().substring(0, 1).toUpperCase()).append(".").toString());

        List<InsuranceDto> insuranceDtos = patientEntity.getInsurances().stream().map(this::convertInsuranceToDto).collect(Collectors.toList());

        List<TreatmentDto> treatmentDtos = patientEntity.getTreatments().stream().map(this::convertTreatmentToDto).collect(Collectors.toList());

        PatientDto patientDto = new PatientDto();
        patientDto.setUserDto(user);
        patientDto.setId(id);
        patientDto.setInsurances(insuranceDtos);
        patientDto.setTreatments(treatmentDtos);

        return patientDto;
    }

    private InsuranceDto convertInsuranceToDto(Insurance insurance) {
        InsuranceDto insuranceDto = new InsuranceDto();
        insuranceDto.setInsuranceName(insurance.getName());
        insuranceDto.setInsuranceNumber(insurance.getNumber());
        insuranceDto.setStartDate(insurance.getStartDate());
        insuranceDto.setEndDate(insurance.getEndDate());
        return insuranceDto;
    }

    private TreatmentDto convertTreatmentToDto(Treatment treatment) {
        TreatmentDto treatmentDto = new TreatmentDto();
        treatmentDto.setDiagnosis(treatment.getDiagnosis());
        treatmentDto.setStartDate(treatment.getStartDate());
        treatmentDto.setEndDate(treatment.getEndDate());
        treatmentDto.setStatus(String.valueOf(treatment.isStatus()));
        return treatmentDto;
    }

    @Override
    public List<PatientDto> convertToDTO(List<Patient> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
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

}
