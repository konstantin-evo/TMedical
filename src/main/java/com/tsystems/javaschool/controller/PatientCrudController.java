package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.service.api.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/patient")
public class PatientCrudController {

    private final PatientService patientService;

    @Autowired
    public PatientCrudController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/new")
    public String addPatient(@ModelAttribute PatientDto patient) {
        patientService.save(patient);
        return "patient/new";
    }

}
