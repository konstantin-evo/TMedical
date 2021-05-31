package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.service.api.PatientService;
import com.tsystems.javaschool.service.api.TherapyService;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/therapy")
public class TherapyController {

    private final TherapyService therapyService;

    @Autowired
    public TherapyController(TherapyService therapyService) {
        this.therapyService = therapyService;
    }

    @GetMapping("/{day}")
    public String therapiesByDay(@PathVariable("day") String day, Model model){
        List<TherapyCaseDto> therapyCases = therapyService.findCasesByDay(day);
        model.addAttribute("therapyCases", therapyCases);
        return "therapy/therapy-day";
    }

}
