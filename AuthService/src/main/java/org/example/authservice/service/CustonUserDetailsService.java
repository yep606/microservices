package org.example.authservice.service;

import lombok.RequiredArgsConstructor;
import org.example.authservice.repo.UserCredentialRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustonUserDetailsService implements UserDetailsService {

    private final UserCredentialRepo userCredentialRepo;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        org.example.authservice.entity.UserDetails user = userCredentialRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
