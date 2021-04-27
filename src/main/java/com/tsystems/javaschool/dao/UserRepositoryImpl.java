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
    public List<UserEntity> findAll() {
        Query query = session.getCurrentSession().createQuery("from UserEntity");
        return query.list();
    }

    @Override
    public UserEntity findById(int id) {
        return (UserEntity) session.getCurrentSession()
                .createQuery("FROM UserEntity WHERE UserEntity.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public UserEntity findByEmail(String email) {
        return (UserEntity) session.getCurrentSession()
                .createQuery("FROM UserEntity WHERE UserEntity.email = :email")
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public void add(UserEntity user) {
        session.getCurrentSession().save(user);
    }

    @Override
    public void update(UserEntity user) {
        session.getCurrentSession().merge(user);
    }

    @Override
    public List<UserEntity> findRolesByEmail(String email) {
        Query query = (Query) session.getCurrentSession()
                .createQuery("SELECT role FROM UserEntity WHERE UserEntity.email = :email")
                .setParameter("email", email).getSingleResult();

        return query.list();
    }
}
