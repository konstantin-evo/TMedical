package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.dao.api.MedicamentationRepository;
import com.tsystems.javaschool.model.entity.Medication;
import com.tsystems.javaschool.model.entity.Therapy;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicamentationRepositoryImpl implements MedicamentationRepository {
    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public List<Medication> findAllMedication() {
        Query query = session.getCurrentSession().createQuery("from Medication");
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Medication> findMedicationByType(String type) {
        return (List<Medication>) session.getCurrentSession()
                .createQuery("FROM Medication AS m WHERE m.type = :type")
                .setParameter("type", type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Medication findMedicationByTherapy(Therapy therapy) {
        return (Medication) session.getCurrentSession()
                .createQuery("FROM Medication AS m JOIN Therapy AS t ON m.id = t.medication.id WHERE t.id = :therapy")
                .setParameter("therapy", therapy).getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Medication findMedicationByName(String name) {
        return (Medication) session.getCurrentSession()
                .createQuery("FROM Medication AS m WHERE m.name = :name")
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public void add(Medication medication) {
        session.getCurrentSession().save(medication);
    }
}
