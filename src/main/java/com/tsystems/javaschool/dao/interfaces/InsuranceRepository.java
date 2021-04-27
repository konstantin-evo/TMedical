package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.model.entity.Insurance;
import com.tsystems.javaschool.model.entity.Patient;

import java.util.List;

public interface InsuranceRepository {
    /**
     * Find Insurance by surname
     * @param surname the patient
     * @return the list<Insurance> entities
     */
    List<Insurance> findInsuranceBySurname(String surname);


    void add(Insurance insurance);
}
