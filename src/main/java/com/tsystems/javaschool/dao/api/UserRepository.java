package com.tsystems.javaschool.dao.api;

import com.tsystems.javaschool.model.entity.UserEntity;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends AbstractRepository<UserEntity, Integer> {

    List<UserEntity> findAll();

    /**
     * Find by id user entity.
     * @param id the id
     * @return the user entity
     */
    UserEntity findById(Integer id);

    /**
     * Find by email user entity.
     * @param email the email
     * @return the user entity
     */
    UserEntity findByEmail(String email);

    /**
     * Add user entity.
     *
     * @param user the user entity
     * @return the user entity
     */
    void save(UserEntity user);

    /**
     * Update user entity.
     *
     * @param user the user entity
     * @return the user entity
     */
    UserEntity update(UserEntity user);

    /**
     * Find roles by user email list.
     *
     * @param email the user login
     * @return the list
     */
    Collection<UserEntity> findRolesByEmail(String email);

    List<UserEntity> findPatientByDoctor(int id);

    String getRole();
}
