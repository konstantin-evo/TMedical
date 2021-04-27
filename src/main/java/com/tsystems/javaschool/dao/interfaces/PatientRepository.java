package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.model.entity.Patient;
import com.tsystems.javaschool.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository {

    List<UserEntity> findAllPatient();

    /**
     * Find Patient by id.
     * @param id the patient id
     * @return the Patient entities
     */
    Patient findPatientById(int id);

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

    /**
     * Add Patient entity.
     * @param patient the patient entity
     * @return the patient entity
     */
    void add(Patient patient);
}
