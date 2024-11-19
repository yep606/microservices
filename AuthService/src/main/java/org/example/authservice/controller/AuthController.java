package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.dto.AuthDto;
import org.example.authservice.entity.UserDetails;
import org.example.authservice.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public String registerNewUser(@RequestBody UserDetails userDetails) {
        return service.register(userDetails);
    }
    @PostMapping("/token")
    public String getJwtToken(@RequestBody AuthDto dto) {
        log.info("Incomind dto {}", dto);
        return service.getToken(dto);
    }
}
