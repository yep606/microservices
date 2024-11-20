package ru.rogoff.educationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.rogoff.dto.CourseResponseDto;

import java.util.List;
import java.util.UUID;

@FeignClient("COURSE-SERVICE")
public interface CoursesFeignClient {
    @GetMapping("/courses/{cvUuid}")
    List<CourseResponseDto> get(@PathVariable UUID cvUuid);
}
