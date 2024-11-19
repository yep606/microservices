package ru.rogoff.courseservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rogoff.courseservice.service.CourseService;
import ru.rogoff.dto.CourseResponseDto;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/courses/{cvUuid}")
public class CourseController {

    private final CourseService service;
    @GetMapping
    public List<CourseResponseDto> get(@PathVariable UUID cvUuid) {
        return service.getByCvUuid(cvUuid);
    }

}
