package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.PatientDto;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.service.api.PatientService;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String addTreatment(@PathVariable("id") int id, @ModelAttribute("treatment") TreatmentDto treatment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        treatmentService.save(treatment, email, id);
        return "redirect:/treatment/all";
    }

    @GetMapping("/{id}")
    public String showTreatment(@PathVariable("id") int id, Model model) {
        TreatmentDto treatment = treatmentService.findById(id);
        List<TherapyDto> therapy = treatment.getTherapies();
        model.addAttribute("treatment", treatment);
        model.addAttribute("therapy", therapy);
        TherapyDto dto = new TherapyDto();
        model.addAttribute("therapyPost", dto);
        return "treatment/treatment-show";
    }

    @PostMapping(value = "/{id}")
    public String addTherapy(@PathVariable("id") int id, @ModelAttribute("therapyPost") TherapyDto dto) {
       treatmentService.addTherapy(id, dto);
       return "redirect:/treatment/all";
    }

}
