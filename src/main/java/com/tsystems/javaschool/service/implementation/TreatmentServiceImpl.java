package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.model.dto.UserDto;
import com.tsystems.javaschool.model.entity.Patient;
import com.tsystems.javaschool.model.entity.Treatment;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl extends AbstractServiceImpl<Treatment, TreatmentRepository, TreatmentDto, Integer> implements TreatmentService {

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository dao, ModelMapper mapper) {
        super(dao, mapper, TreatmentDto.class, Treatment.class);
    }

    @Override
    public TreatmentDto convertToDTO(Treatment treatment) {
        String diagnosis = treatment.getDiagnosis();
        LocalDate startDate = treatment.getStartDate();
        LocalDate endDate = treatment.getEndDate();
        String status = String.valueOf(treatment.isStatus());

        PatientDto patient = new PatientDto();
        patient.setId(treatment.getPatient().getId());

        UserDto user = new UserDto();
        user.setSurname(treatment.getPatient().getUser().getSurname());
        user.setFirst_name(treatment.getPatient().getUser().getFirst_name());
        user.setMiddle_name(treatment.getPatient().getUser().getMiddle_name());
        user.setDbirth(treatment.getPatient().getUser().getDbirth());
        user.setGender(treatment.getPatient().getUser().getGender());
        user.setAddress(treatment.getPatient().getUser().getAddress());
        user.setEmail(treatment.getPatient().getUser().getEmail());

        patient.setUserDto(user);

        String doctor = treatment.getDoctor().getFirst_name() + " " + treatment.getDoctor().getSurname();

        TreatmentDto treatmentDto = new TreatmentDto();
        treatmentDto.setPatientDto(patient);
        treatmentDto.setStatus(status);
        treatmentDto.setDiagnosis(diagnosis);
        treatmentDto.setStartDate(startDate);
        treatmentDto.setEndDate(endDate);
        treatmentDto.setDoctor(doctor);

        return treatmentDto;
    }

    @Override
    public List<TreatmentDto> convertToDTO(List<Treatment> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
