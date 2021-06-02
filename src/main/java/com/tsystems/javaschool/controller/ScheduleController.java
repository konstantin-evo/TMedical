package com.tsystems.javaschool.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.TherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final TherapyService service;
    private final MapperService mapper;

    @Autowired
    public ScheduleController(TherapyService service, MapperService mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "", produces = { "application/json;**charset=UTF-8**" })
    public String sendSchedule() throws JsonProcessingException {


        String day = String.valueOf(LocalDate.now());
        List<TherapyCaseDto> therapyCases = service.findCasesByDay(day);
        String json = mapper.converToJson(therapyCases);
        return json;
    }

}
