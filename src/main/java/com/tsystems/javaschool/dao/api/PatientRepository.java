package com.tsystems.javaschool.dao.api;

import com.tsystems.javaschool.model.entity.Patient;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends AbstractRepository<Patient, Integer> {

    /**
     * Find Patient by Insurance number
     * @param number the patient id
     * @return the Patient entities
     */
    Patient findPatientByInsurance(String number);

    /**
     * Find Patient by surname
     * @param surname the patient id
     * @return the list<Patient> entities
     */
    List<Patient> findPatientBySurname(String surname);

    /**
     * Find Patient by Birthday
     * @param dbirth the patient id
     * @return the list<Patient> entities
     */
    List<Patient> findPatientByBirthday(LocalDate dbirth);

    List<Patient> findPatientByDoctor(int id);

    Patient findPatientByUser(int id);

}
