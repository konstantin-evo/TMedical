package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.service.api.TherapyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TherapyServiceImpl extends AbstractServiceImpl<Therapy, TherapyRepository, TherapyDto, Integer> implements TherapyService {

    @Autowired
    public TherapyServiceImpl(TherapyRepository dao, ModelMapper mapper) {
        super(dao, mapper, TherapyDto.class, Therapy.class);
    }

    @Override
    public TherapyDto convertToDTO(Therapy therapy) {
        TherapyDto therapyDto = new TherapyDto();
        therapyDto.setType(therapy.getMedication().getType());
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

}
