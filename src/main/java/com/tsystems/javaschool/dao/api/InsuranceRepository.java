package com.tsystems.javaschool.dao.api;

import com.tsystems.javaschool.model.entity.Insurance;
import java.util.List;

public interface InsuranceRepository extends AbstractRepository<Insurance, Integer> {
    /**
     * Find Insurance by surname
     * @param surname the patient
     * @return the list<Insurance> entities
     */
    List<Insurance> findInsuranceBySurname(String surname);
}
