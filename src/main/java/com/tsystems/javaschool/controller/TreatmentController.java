package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.service.api.PatientService;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ModelAttribute("treatment")
    public TreatmentDto treatmentDto() {
        return new TreatmentDto();
    }

    @ModelAttribute("treatmentList")
    public List<TreatmentDto> treatmentsDto() {
        return treatmentService.findAll();
    }

    @GetMapping("/all")
    public String index(@ModelAttribute("treatmentList") @Valid List<TreatmentDto> treatmentList) {
        treatmentList = treatmentService.findAll();
        return "treatment/all-treatments";
    }

    @GetMapping("/patient/{id}")
    public String patientTreatments(@PathVariable("id") int id, Model model) {
        List<TreatmentDto> treatmentList = treatmentService.findByPatientId(id);
        model.addAttribute("treatmentList", treatmentList);
        return "treatment/patient-treatment";
    }

    @GetMapping("/add/{id}")
    public String newTreatment(@PathVariable("id") int id, Model model) {
        PatientDto patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "treatment/add-treatment";
    }

    @PostMapping(value = "/add/{id}")
    public String addTreatment(@PathVariable("id") int id, @ModelAttribute("treatment") @Valid TreatmentDto treatment) {
        treatmentService.save(treatment);
        return "redirect:all";
    }

}
