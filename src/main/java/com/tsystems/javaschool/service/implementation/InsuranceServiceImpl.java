package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.InsuranceRepository;
import com.tsystems.javaschool.model.dto.InsuranceDto;
import com.tsystems.javaschool.model.entity.Insurance;
import com.tsystems.javaschool.service.api.InsuranceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl extends AbstractServiceImpl<Insurance, InsuranceRepository, InsuranceDto, Integer> implements InsuranceService {

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository dao, ModelMapper mapper) {
        super(dao, mapper, InsuranceDto.class, Insurance.class);
    }
}
