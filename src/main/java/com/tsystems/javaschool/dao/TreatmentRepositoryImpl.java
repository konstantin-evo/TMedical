package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.model.entity.Treatment;
import com.tsystems.javaschool.dao.api.TreatmentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class TreatmentRepositoryImpl implements TreatmentRepository {

    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public Treatment findById(Integer id) {
        return (Treatment) session.getCurrentSession()
                .createQuery("FROM Treatment AS t WHERE t.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Treatment> findAll() {
        Query query = session.getCurrentSession().createQuery("from Treatment");
        return query.list();
    }

    @Override
    public Treatment save(Treatment treatment) {
        int id = (Integer) session.getCurrentSession().save(treatment);
        return null;
    }

    @Override
    public Treatment update(Treatment treatment) {
        return (Treatment) session.getCurrentSession().merge(treatment);
    }

    @Override
    public Collection<Treatment> findTreatmentByPatientId(int id) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM Treatment AS t WHERE t.patient.id = :id")
                .setParameter("id", id);

        return query.list();
    }

    @Override
    public Collection<Treatment> findTreatmentByDoctorId(int doctor_id) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM Treatment AS t WHERE t.doctor = :doctor_id")
                .setParameter("doctor_id", doctor_id);
        return query.list();
    }

    @Override
    public List<Therapy> findTherapiesByTreatmentId(int id) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM Treatment AS tr JOIN Therapy as th ON tr.id = th.treatment.id WHERE tr.id = :id")
                .setParameter("id", id);
        return query.list();
    }

}
