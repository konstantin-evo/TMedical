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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private UserRepository userRepository;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRepository.getRole());
        return Arrays.asList(authority);
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(UserEntity userEntity) {

      return Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().name()));

    }

}