package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.InsuranceDto;
import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.service.api.InsuranceService;
import com.tsystems.javaschool.service.api.PatientService;
import com.tsystems.javaschool.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping(value = "/add")
    public String addPatient(@ModelAttribute("patient") @Valid PatientDto patient) {
        patientService.save(patient);
        return "redirect:all";
    }

    @GetMapping("/add")
    public String newPatient() {
        return "patient/add-patient";
    }

    @GetMapping("/all")
    public String index(Model model) {
        List<PatientDto> patientList = patientService.findAll();
        model.addAttribute("patientList", patientList);
        return "patient/all-patient";
    }

}
