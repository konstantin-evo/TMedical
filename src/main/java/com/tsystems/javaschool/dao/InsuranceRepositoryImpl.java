package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.dao.api.InsuranceRepository;
import com.tsystems.javaschool.model.entity.Insurance;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository {
    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public List<Insurance> findInsuranceBySurname(String surname) {
        return (List<Insurance>) session.getCurrentSession()
                .createQuery("FROM Patient AS p JOIN Insurance AS i ON p.id = i.patient.id JOIN UserEntity AS u ON u.id = p.user.id WHERE u.surname = :surname")
                .setParameter("surname", surname);
    }

    @Override
    public Insurance save(Insurance insurance) {
        return (Insurance) session.getCurrentSession().save(insurance);
    }

    @Override
    public Insurance findById(Integer id) {
        return (Insurance) session.getCurrentSession()
                .createQuery("FROM Insurance WHERE Insurance.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Insurance> findAll() {
        Query query = session.getCurrentSession().createQuery("FROM Insurance ");
        return query.list();
    }

    @Override
    public Insurance update(Insurance insurance) {
        return (Insurance) session.getCurrentSession().merge(insurance);
    }
}
