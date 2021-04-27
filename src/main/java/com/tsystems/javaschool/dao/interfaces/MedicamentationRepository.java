package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.model.entity.Medication;
import com.tsystems.javaschool.model.entity.Therapy;

import java.util.List;

public interface MedicamentationRepository {
    List<Medication> findAllMedication();

    List<Medication> findMedicationByType(String type);

    Medication findMedicationByTherapy(Therapy therapy);

    Medication findMedicationByName(String name);

    void add(Medication medication);
}
