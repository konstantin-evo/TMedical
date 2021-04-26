package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.entity.Treatment;
import com.tsystems.javaschool.dao.interfaces.TreatmentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class TreatmentRepositoryImpl implements TreatmentRepository {

    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public List<Treatment> findAll() {
        Query query = session.getCurrentSession().createQuery("from Treatment");
        List list = query.list();
        return list;
    }

    @Override
    public Treatment findById(int id) {
        return (Treatment) session.getCurrentSession()
                .createQuery("FROM Treatment WHERE id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Collection<Treatment> findByUserId(int patient_id) {
        return null;
    }

    @Override
    public Collection<Treatment> findByDoctorId(int doctor_id) {
        return null;
    }

    @Override
    public void add(Treatment treatment) {

    }

    @Override
    public void update(Treatment treatment) {

    }
}
