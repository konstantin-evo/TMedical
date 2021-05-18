package com.tsystems.javaschool.dao.api;

import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.model.entity.Treatment;

import java.util.Collection;
import java.util.List;

public interface TreatmentRepository extends AbstractRepository<Treatment, Integer> {
    List<Treatment> findAll();

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

    List<Therapy> findTherapiesByTreatmentId(int id);
}
