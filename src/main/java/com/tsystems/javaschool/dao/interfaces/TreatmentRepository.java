package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.model.entity.Treatment;

import java.util.Collection;
import java.util.List;

public interface TreatmentRepository {
    List<Treatment> findAll();

    /**
     * Find by id Treatment entity.
     * @param id the id
     * @return the Treatment
     */
    Treatment findById(int id);

    /**
     * Find Treatment by user id.
     * @param patient_id the email
     * @return the List<Treatment> entities
     */
    Collection<Treatment> findTreatmentByPatientId(int patient_id);

    /**
     * Find Treatment by doctor id.
     * @param doctor_id the email
     * @return the List<Treatment> entities
     */
    Collection<Treatment> findTreatmentByDoctorId(int doctor_id);

    /**
     * Add treatment entity.
     * @param treatment the treatment entity
     * @return the treatment entity
     */
    void add(Treatment treatment);

    /**
     * Update treatment entity.
     * @param treatment the treatment entity
     * @return the treatment entity
     */

}
