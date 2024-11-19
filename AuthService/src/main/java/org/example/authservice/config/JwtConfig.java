package org.example.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rogoff.jwt.JwtTokenUtil;

@Configuration
public class JwtConfig {

    @Bean
    public JwtTokenUtil jwtTokenUtil(@Value("${jwt.secret-key}") String secretKey) {
        return new JwtTokenUtil(secretKey);
    }
}
