package com.tsystems.javaschool.dao.api;

import com.tsystems.javaschool.model.entity.Therapy;

import java.util.Collection;

public interface TherapyRepository  extends AbstractRepository<Therapy, Integer> {
    /**
     * Find Therapies by Treatment id.
     * @param treat_id the treatment_id
     * @return the List<Therapy> entities
     */
    Collection<Therapy> findTherapyByTreatmentId(int treat_id);

    /**
     * Find Therapies by patient_id id.
     * @param patient_id the treatment_id
     * @return the List<Therapy> entities
     */
    Collection<Therapy> findTherapyByPatientId(int patient_id);

    /**
     * Find Therapies by patient_id id.
     * @param doctor_id the treatment_id
     * @return the List<Therapy> entities
     */
    Collection<Therapy> findTherapyByDoctorId(int doctor_id);

    void deleteTherapy(int id);

}
