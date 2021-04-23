package com.tsystems.javaschool.repository;

import com.tsystems.javaschool.model.entity.User;

import java.util.List;


public interface UserRepository {

    List<User> findAll();

    /**
     * Find by id user entity.
     * @param id the id
     * @return the user entity
     */
    User findById(int id);

    /**
     * Find by email user entity.
     * @param email the login
     * @return the user entity
     */

    User findByEmail(String email);

    /**
     * Add user entity.
     *
     * @param user the user entity
     * @return the user entity
     */
    void add(User user);

    /**
     * Update user entity.
     *
     * @param user the user entity
     * @return the user entity
     */
    void update(User user);

}
