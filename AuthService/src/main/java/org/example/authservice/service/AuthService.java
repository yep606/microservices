package org.example.authservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.dto.AuthDto;
import org.example.authservice.entity.UserDetails;
import org.example.authservice.repo.UserCredentialRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rogoff.jwt.JwtTokenUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserCredentialRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public String register(UserDetails user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDetails savedUser = repo.save(user);
        return "User with username %s added to system".formatted(savedUser.getUsername());
    }

    public String getToken(AuthDto dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
        ));
        if (authentication.isAuthenticated()) {
            return jwtTokenUtil.generate(dto.getUsername(), 1000 * 60 * 100);
        }
        throw new RuntimeException("Invalid access!");
    }
}
