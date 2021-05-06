package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.model.dto.InsuranceDto;
import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.model.dto.UserDto;
import com.tsystems.javaschool.model.entity.Insurance;
import com.tsystems.javaschool.model.entity.Patient;
import com.tsystems.javaschool.model.entity.enums.Gender;
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
        user.setFirst_name(patientEntity.getUser().getFirst_name());
        user.setMiddle_name(patientEntity.getUser().getMiddle_name());
        user.setDbirth(patientEntity.getUser().getDbirth());
        user.setGender(patientEntity.getUser().getGender());
        user.setAddress(patientEntity.getUser().getAddress());
        user.setEmail(patientEntity.getUser().getEmail());

        List<InsuranceDto> insuranceDtos = patientEntity.getInsurances().stream().map(this::convertInsuranceToDto).collect(Collectors.toList());

        PatientDto patientDto = new PatientDto();
        patientDto.setUserDto(user);
        patientDto.setId(id);
        patientDto.setInsurances(insuranceDtos);

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

    @Override
    public List<PatientDto> convertToDTO(List<Patient> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

}
