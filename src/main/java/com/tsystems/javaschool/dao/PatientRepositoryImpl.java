package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.dao.api.PatientRepository;
import com.tsystems.javaschool.model.entity.Patient;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {

    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public Patient findPatientByInsurance(String number) {
        return (Patient) session.getCurrentSession()
                .createQuery("FROM Patient AS p JOIN Insurance AS i ON p.id = i.patient.id WHERE i.number = :number")
                .setParameter("number", number).getSingleResult();
    }

    @Override
    public List<Patient> findPatientBySurname(String surname) {
        return (List<Patient>) session.getCurrentSession()
                .createQuery("FROM Patient AS p JOIN UserEntity AS u ON p.user.id = u.id WHERE u.surname = :surname")
                .setParameter("surname", surname);
    }

    @Override
    public List<Patient> findPatientByBirthday(LocalDate dbirth) {
        return (List<Patient>) session.getCurrentSession()
                .createQuery("FROM Patient AS p JOIN UserEntity AS u ON p.user.id = u.id WHERE u.dbirth = :dbirth")
                .setParameter("dbirth", dbirth);
    }

    @Override
    public List<Patient> findPatientByDoctor(int id) {
        return (List<Patient>) session.getCurrentSession()
                .createQuery("FROM Patient AS p JOIN Treatment AS tr ON p.id = tr.patient.id WHERE tr.doctor = :id")
                .setParameter("id", id);
    }

    @Override
    public Patient findById(Integer id) {
        return (Patient) session.getCurrentSession()
                .createQuery("FROM Patient WHERE Patient.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Patient> findAll() {
        Query query = session.getCurrentSession().createQuery("from Patient");
        return query.list();
    }

    @Override
    public Patient save(Patient patient) {
        int id = (Integer) session.getCurrentSession().save(patient);
        return null;
    }

    @Override
    public Patient update(Patient patient) {
        return (Patient) session.getCurrentSession().merge(patient);
    }

    @Override
    public Patient findPatientByUser(int id) {
        return (Patient) session.getCurrentSession()
                .createQuery("FROM Patient AS p JOIN UserEntity AS u ON p.user.id = u.id WHERE u.id = :id")
                .setParameter("id", id).getSingleResult();
    }
}
