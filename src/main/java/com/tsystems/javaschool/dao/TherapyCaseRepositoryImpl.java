package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.dao.api.TherapyCaseRepository;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.model.entity.TherapyCase;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TherapyCaseRepositoryImpl implements TherapyCaseRepository {

    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public List<TherapyCase> findTherapyCaseByNurse(String surname) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM TherapyCase AS th JOIN UserEntity as u ON th.nurse.id = u.id WHERE u.surname = :surname")
                .setParameter("surname", surname);

        return query.list();
    }

    @Override
    public List<TherapyCase> findTherapyCaseByPatient(String surname) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM TherapyCase AS thc JOIN Therapy as th ON thc.therapy.id = th.id " +
                "JOIN Treatment AS tr ON tr.id = th.treatment.id JOIN Patient AS p ON p.id = tr.patient.id JOIN UserEntity AS u ON u.id = p.user.id WHERE u.surname = :surname")
                .setParameter("surname", surname);

        return query.list();
    }

    @Override
    public List<TherapyCase> findTherapyCaseByDoctor(String surname) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM TherapyCase AS thc JOIN Therapy as th ON thc.therapy.id = th.id " +
                        "JOIN Treatment AS tr ON tr.id = th.treatment.id JOIN UserEntity AS u ON u.id = tr.doctor.id WHERE u.surname = :surname")
                .setParameter("surname", surname);

        return query.list();
    }

    @Override
    public List<TherapyCase> findTherapyCaseByDate(LocalDate date) {
        Query query = session.getCurrentSession()
                .createQuery("FROM TherapyCase AS th WHERE th.date= :date")
                .setParameter("date", date);

        return query.list();
    }

    @Override
    public List<TherapyCase> findTherapyCaseByStatus(String status) {
        Query query = session.getCurrentSession()
                .createQuery("FROM TherapyCase AS th WHERE th.status= :status")
                .setParameter("status", status);

        return query.list();
    }


    @Override
    public TherapyCase findById(Integer id) {
        return (TherapyCase) session.getCurrentSession()
                .createQuery("FROM TherapyCase AS t WHERE t.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<TherapyCase> findAll() {
        Query query = session.getCurrentSession().createQuery("FROM TherapyCase");
        return query.list();
    }

    @Override
    public TherapyCase save(TherapyCase therapyCase) {
        int id = (Integer) session.getCurrentSession().save(therapyCase);
        return null;
    }

    @Override
    public TherapyCase update(TherapyCase therapyCase) {
        return (TherapyCase) session.getCurrentSession().merge(therapyCase);
    }
}
