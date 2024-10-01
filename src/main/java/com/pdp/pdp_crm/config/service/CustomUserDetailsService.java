package com.pdp.pdp_crm.config.service;

import com.pdp.pdp_crm.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findUserByPhoneNumber(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(username)
                );
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}