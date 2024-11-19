package ru.rogoff.maineducationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rogoff.dto.MainEducationResponseDto;
import ru.rogoff.maineducationservice.service.MainEducationService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/main-education/{cvUuid}")
public class MainEducationController {

    private final MainEducationService service;
    @GetMapping
    public List<MainEducationResponseDto> get(@PathVariable UUID cvUuid) {
        return service.getByCvUuid(cvUuid);
    }

}
