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
    public List<Patient> findAllPatient() {
        Query query = session.getCurrentSession().createQuery("from Patient");
        return query.list();
    }

    @Override
    public Patient findPatientById(int id) {
        return (Patient) session.getCurrentSession()
                .createQuery("FROM Patient WHERE Patient.id = :id")
                .setParameter("id", id).getSingleResult();
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
    public void add(Patient patient) {
            session.getCurrentSession().save(patient);
    }
}
