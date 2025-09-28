package com.example.service;

import com.example.entity.AppUser;
import com.example.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getIsActive() != null && !user.getIsActive()) {
            throw new UsernameNotFoundException("User is disabled");
        }

        String password = user.getPassword();
        if (password == null || password.isEmpty()) {
            throw new UsernameNotFoundException("User has no password set");
        }

        // Return Spring Security User
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                password,
                Collections.singletonList(new SimpleGrantedAuthority(
                        "ROLE_" + (user.getRole() !=null ? user.getRole().name() : "USER")))
        );
    }
}
