package ru.rogoff.educationservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rogoff.dto.CourseResponseDto;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class CoursesClient {
    private final WebClient webClient;

    public CoursesClient(WebClient.Builder webClientBuilder, @Value("${integration.course.host}") String courseUrl) {
        if (courseUrl == null || courseUrl.isBlank()) {
            throw new IllegalArgumentException("Host is not specified for CoursesClient");
        }
        this.webClient = webClientBuilder
                .baseUrl(courseUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public Mono<List<CourseResponseDto>> getCourses(UUID cvUuid) {
        log.info("Make async request to Courses service with cvUuid: {}", cvUuid);

        return webClient.get()
                .uri("/courses/{cvUuid}", cvUuid)
                .retrieve()
                .bodyToFlux(CourseResponseDto.class)
                .collectList();
    }
}
