package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.model.entity.UserEntity;
import com.tsystems.javaschool.dao.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        criteriaQuery
                .select(root);
        TypedQuery<UserEntity> selectAll = entityManager.createQuery(criteriaQuery);

        return selectAll.getResultList();
    }

    @Override
    public UserEntity findById(int id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public UserEntity findByEmail(String email) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
//        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
//
//        criteriaQuery
//                .select(root)
//                .where(criteriaBuilder.equal(root.get(UserEntity.email), email));
//        TypedQuery<UserEntity> selectByEmail = entityManager.createQuery(criteriaQuery);
//
//        return selectByEmail.getResultStream().findFirst().orElse(null);
        return null;
    }

    @Override
    public void add(UserEntity user) {
        entityManager.persist(user);
    }

    @Override
    public void update(UserEntity user) {
        entityManager.merge(user);
    }

    @Override
    public Collection<UserEntity> findRolesByEmail(String email) {
        return null;
    }
}
