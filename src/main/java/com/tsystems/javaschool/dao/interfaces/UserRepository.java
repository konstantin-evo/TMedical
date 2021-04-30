package com.tsystems.javaschool.dao.interfaces;

import com.tsystems.javaschool.model.entity.UserEntity;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    List<UserEntity> findAll();

    /**
     * Find by id user entity.
     * @param id the id
     * @return the user entity
     */
    UserEntity findById(int id);

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
    void add(UserEntity user);

    /**
     * Update user entity.
     *
     * @param user the user entity
     * @return the user entity
     */
    void update(UserEntity user);

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
