package com.tsystems.javaschool.dao.api;

import com.tsystems.javaschool.model.entity.TherapyCase;

import java.time.LocalDate;
import java.util.List;

public interface TherapyCaseRepository {

    List<TherapyCase> findAllTherapyCase();

    List<TherapyCase> findTherapyCaseByNurse(String surname);

    List<TherapyCase> findTherapyCaseByPatient(String surname);

    List<TherapyCase> findTherapyCaseByDoctor(String surname);

    List<TherapyCase> findTherapyCaseByDate(LocalDate date);

    List<TherapyCase> findTherapyCaseByStatus(String status);

}
