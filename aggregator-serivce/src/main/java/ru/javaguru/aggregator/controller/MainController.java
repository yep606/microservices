package ru.javaguru.aggregator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaguru.aggregator.dto.DocumentRequestDto;
import ru.javaguru.aggregator.service.DocumentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/aggregator")
public class MainController {

    private final DocumentService service;

    @PostMapping("/collect")
    public ResponseEntity<String> collect(DocumentRequestDto dto) {
        log.debug("Input data to collect info: {}", dto);
        service.collectData(dto);
        return ResponseEntity.ok("OK");
    }
}
