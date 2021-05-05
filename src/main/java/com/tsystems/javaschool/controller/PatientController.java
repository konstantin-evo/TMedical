package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.service.api.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @ModelAttribute("patient")
    public PatientDto patientDto() {
        return new PatientDto();
    }

    @PostMapping(value = "/addPatient")
    public String addPatient(@ModelAttribute("patient")@Valid PatientDto patient) {
        patientService.save(patient);
        return "redirect:patient/index";
    }

    @GetMapping("/add-patient")
    public String newPatient() {
        return "patient/add-patient";
    }

    @GetMapping("/index")
    public String index(@ModelAttribute PatientDto patient) {
        //patientService.findAll();
        return "patient/index";
    }

}
