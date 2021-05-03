package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.entity.UserEntity;
import com.tsystems.javaschool.dao.interfaces.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.session = sessionFactory;
    }

    @Override
    public UserEntity findById(Integer id) {
        return (UserEntity) session.getCurrentSession()
                .createQuery("FROM UserEntity WHERE UserEntity.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<UserEntity> findAll() {
        Query query = session.getCurrentSession().createQuery("from UserEntity");
        return query.list();
    }

    @Override
    public UserEntity findByEmail(String email) {
        return (UserEntity) session.getCurrentSession()
                .createQuery("FROM UserEntity AS u WHERE u.email = :email")
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return (UserEntity) session.getCurrentSession().save(user);
    }

    @Override
    public UserEntity update(UserEntity user) {
        return (UserEntity) session.getCurrentSession().merge(user);
    }

    @Override
    public List<UserEntity> findRolesByEmail(String email) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("SELECT role FROM UserEntity WHERE UserEntity.email = :email")
                .setParameter("email", email);

        return query.list();
    }

    @Override
    public List<UserEntity> findPatientByDoctor(int id) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("FROM UserEntity AS up JOIN Patient AS p ON p.user.id = up.id JOIN Treatment AS tr ON tr.patient.id = p.id JOIN UserEntity AS ud ON tr.doctor.id = ud.id WHERE ud.id = :id")
                .setParameter("id", id);

        return query.list();
    }

    @Override
    public String getRole() {
        return String.valueOf(session.getCurrentSession()
                .createQuery("SELECT role FROM UserEntity"));
    }

}
