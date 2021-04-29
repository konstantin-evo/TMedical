package com.tsystems.javaschool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsystems.javaschool.model.entity.UserEntity;
import com.tsystems.javaschool.dao.interfaces.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = new UserEntity();
        try {
            userEntity = userRepository.findByEmail(email);
        } catch (Exception e) {
            log.error("Error getting user by email", e);
        }

        if (userEntity.getEmail() == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }
        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                mapRolesToAuthorities(userEntity)
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(UserEntity userEntity) {

      return Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().name()));

    }

}