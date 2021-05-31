package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.service.api.TherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("day", day);
        return "therapy/therapy-day";
    }

    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable("id") int id, @ModelAttribute("casePost") TherapyCaseDto therapyCaseDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        therapyService.setStatus(id, email, "DONE");
        return "redirect:/home";
    }

}
