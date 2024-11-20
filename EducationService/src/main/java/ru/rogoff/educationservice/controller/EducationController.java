package ru.rogoff.educationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rogoff.dto.EducationResponseDto;
import ru.rogoff.educationservice.service.EducationServiceImpl;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/education/{cvUuid}")
@RequiredArgsConstructor
public class EducationController {

    private final EducationServiceImpl service;

//    @GetMapping
//    public Mono<EducationResponseDto> getEducation(@PathVariable UUID cvUuid) {
//        log.info("Incoming GET request in EducationController with cvUuid {}", cvUuid);
//        return service.getEducation(cvUuid);
//    }
    @GetMapping
    public EducationResponseDto getEducation(@PathVariable UUID cvUuid) {
        log.info("Incoming GET request in EducationController with cvUuid {}", cvUuid);
        return service.getAsyncEducation(cvUuid);
    }
}
