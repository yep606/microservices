package ru.javaguru.aggregator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaguru.aggregator.dto.DocumentRequestDto;
import ru.javaguru.aggregator.service.DocumentService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/aggregator")
public class MainController {

    private final DocumentService service;

    //todo: validate reqUuid
    @PostMapping("/collect")
    public ResponseEntity<String> collect(UUID reqUuid, DocumentRequestDto dto) {
        log.debug("Input data to collect info: {}", dto);
        service.collectData(reqUuid, dto);
        return ResponseEntity.ok("OK");
    }
}
