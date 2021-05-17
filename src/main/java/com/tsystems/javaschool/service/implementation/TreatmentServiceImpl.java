package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.model.dto.UserDto;
import com.tsystems.javaschool.model.entity.Treatment;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
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
        String status = ((treatment.isStatus()) ? "On treatment" : "Is discharged");

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
        treatmentDto.setStatus(status);
        treatmentDto.setDiagnosis(diagnosis);
        treatmentDto.setStartDate(startDate);
        treatmentDto.setEndDate(endDate);
        treatmentDto.setDoctor(doctor);
        treatmentDto.setId(treatment.getId());

        return treatmentDto;
    }

    @Override
    public List<TreatmentDto> convertToDTO(List<Treatment> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TreatmentDto> findByPatientId(int id) {
        Collection<Treatment> list = super.getDao().findTreatmentByPatientId(id);
        return convertToDTO((List<Treatment>) list);
    }
}
