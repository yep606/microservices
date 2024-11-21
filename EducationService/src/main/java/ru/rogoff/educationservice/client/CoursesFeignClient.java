package ru.rogoff.educationservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.rogoff.dto.CourseResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@FeignClient(name = "COURSE-SERVICE")
public interface CoursesFeignClient {
    @GetMapping("/courses/{cvUuid}")
    @Retry(name = "course-service", fallbackMethod = "getFallback")
    @CircuitBreaker(name = "course-service", fallbackMethod = "getFallback")
    List<CourseResponseDto> get(@PathVariable UUID cvUuid);

    default List<CourseResponseDto> getFallback(UUID cvUuid, Throwable ex) {
        System.out.println("FALLBACK METHOD EXECUTION");
        System.out.printf("CvId: %s%n", cvUuid);
        System.out.printf("Exception: %s%n", ex.getMessage());
        return Collections.emptyList();
    }
}
