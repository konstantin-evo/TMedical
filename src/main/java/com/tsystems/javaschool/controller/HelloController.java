package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dao.api.UserRepository;
import com.tsystems.javaschool.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public List<UserEntity> test(@RequestBody UserEntity user) {
        System.out.println(user.getSurname());
        List<UserEntity> allPatient = userRepository.findAll();
        return allPatient;
    }

}