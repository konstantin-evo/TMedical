package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.dao.interfaces.UserRepository;
import com.tsystems.javaschool.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final UserRepository userRepository;

    @Autowired
    public PatientController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("patient", userRepository.findAll());
        return "patient/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("patient", userRepository.findById(id));
        return "patient/show";
    }

    @GetMapping("/new")
    public String newPatient(@ModelAttribute("patient") UserEntity user) {
        return "patient/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("patient") UserEntity user) {
        userRepository.add(user);
        return "redirect:/patient";
    }
}
