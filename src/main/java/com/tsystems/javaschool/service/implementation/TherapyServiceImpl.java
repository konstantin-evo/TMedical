package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.MedicamentationRepository;
import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.dao.api.TreatmentRepository;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.service.api.TherapyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TherapyServiceImpl extends AbstractServiceImpl<Therapy, TherapyRepository, TherapyDto, Integer> implements TherapyService {

    public final MedicamentationRepository medicamentationRepository;
    public final TreatmentRepository treatmentRepository;

    @Autowired
    public TherapyServiceImpl(TherapyRepository dao, ModelMapper mapper, MedicamentationRepository medicamentationRepository, TreatmentRepository treatmentRepository) {
        super(dao, mapper, TherapyDto.class, Therapy.class);
        this.medicamentationRepository = medicamentationRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public TherapyDto convertToDTO(Therapy therapy) {
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
    public List<TherapyDto> convertToDTO(List<Therapy> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
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

}
