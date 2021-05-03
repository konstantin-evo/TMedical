package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.UserRepository;
import com.tsystems.javaschool.model.dto.UserDto;
import com.tsystems.javaschool.model.entity.UserEntity;
import com.tsystems.javaschool.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<UserEntity, UserRepository, UserDto, Integer> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository dao, ModelMapper mapper) {
        super(dao, mapper, UserDto.class, UserEntity.class);
    }
}
