package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.service.api.PatientService;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/treatment")
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final PatientService patientService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService, PatientService patientService) {
        this.treatmentService = treatmentService;
        this.patientService = patientService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<PatientDto> patientList = patientService.findAll();
        model.addAttribute("patientList", patientList);
        return "treatment/index";
    }
}
