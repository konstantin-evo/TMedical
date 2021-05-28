package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.model.entity.Therapy;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class TherapyRepositoryImpl implements TherapyRepository {
    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public Collection<Therapy> findTherapyByTreatmentId(int treat_id) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM Therapy AS t WHERE t.treatment.id = :treat_id")
                .setParameter("treat_id", treat_id);

        return query.list();
    }

    @Override
    public Collection<Therapy> findTherapyByPatientId(int patient_id) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM Therapy AS th JOIN Treatment AS tr ON th.treatment.id = tr.id WHERE tr.patient = :patient_id")
                .setParameter("patient_id", patient_id);

        return query.list();
    }

    @Override
    public Collection<Therapy> findTherapyByDoctorId(int doctor_id) {
        return null;
    }


    @Override
    public Therapy findById(Integer id) {
        return (Therapy) session.getCurrentSession()
                .createQuery("FROM Therapy AS t WHERE t.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Therapy> findAll() {
        Query query = session.getCurrentSession().createQuery("from Therapy ");
        return query.list();
    }

    @Override
    public Therapy save(Therapy therapy) {
        int id = (Integer) session.getCurrentSession().save(therapy);
        return findById(id);
    }

    @Override
    public Therapy update(Therapy therapy) {
        return (Therapy) session.getCurrentSession().merge(therapy);
    }
}
