package com.tsystems.javaschool.service.api;

import com.tsystems.javaschool.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {

    /**
     * Gets email of current user.
     *
     * @return the email of current user
     */
    String getEmailOfCurrentUser();

    /**
     * Find user by email user.
     *
     * @param email the email
     * @return the user
     */
    UserEntity findUserByEmail(String email);

    /**
     * Update user.
     *
     * @param user the user
     */
    void update(UserEntity user);

    /**
     * Gets current user.
     *
     * @return the current user
     */
    UserEntity getCurrentUser();

}
