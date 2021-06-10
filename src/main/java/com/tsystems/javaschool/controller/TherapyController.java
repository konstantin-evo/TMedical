package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.controller.exception.ExceptionTreatmentNotFound;
import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.service.api.TherapyService;
import com.tsystems.javaschool.service.api.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/therapy")
public class TherapyController {

    private final TherapyService therapyService;
    private final TreatmentService treatmentService;

    @Autowired
    public TherapyController(TherapyService therapyService, TreatmentService treatmentService) {
        this.therapyService = therapyService;
        this.treatmentService = treatmentService;
    }

    @GetMapping("/{day}")
    public String therapiesByDay(@PathVariable("day") String day, Model model){
        List<TherapyCaseDto> therapyCases = therapyService.findCasesByDay(day);
        model.addAttribute("therapyCases", therapyCases);
        model.addAttribute("day", day);
        return "therapy/therapy-day";
    }

    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable("id") int id) throws ExceptionTreatmentNotFound {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        therapyService.setStatus(id, email, "DONE");
        therapyService.sendMessageByDay(therapyService.findCaseById(id).getDate());
        String treatmentId = String.valueOf(therapyService.findTreatmentByCaseId(id).getId());
        return "redirect:/treatment/"+treatmentId;
    }

    @PostMapping("/delete/{id}")
    public String deleteTherapy(@PathVariable("id") int id) throws ExceptionTreatmentNotFound {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        String treatmentId = String.valueOf(treatmentService.findByTherapyId(id).getId());
        therapyService.deleteTherapy(id, email);
        therapyService.sendMessageByDay(String.valueOf(LocalDate.now()));
        return "redirect:/treatment/"+treatmentId;
    }

}
