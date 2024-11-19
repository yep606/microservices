package ru.rogoff.educationservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rogoff.dto.MainEducationResponseDto;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class MainEducationClient {
    private final WebClient webClient;

    public MainEducationClient(WebClient.Builder webClientBuilder, @Value("${integration.main-education.host}") String mainEducationUrl) {
        if (mainEducationUrl == null || mainEducationUrl.isBlank()) {
            throw new IllegalArgumentException("Host is not specified for MainEducation");
        }
        this.webClient = webClientBuilder.baseUrl(mainEducationUrl).build();
    }

    public Mono<List<MainEducationResponseDto>> getMainEducation(UUID cvUuid) {
        log.info("Make async request to MainEducation service with cvUuid: {}", cvUuid);

        return webClient.get()
                .uri("/main-education/{cvUuid}", cvUuid)
                .retrieve()
                .bodyToFlux(MainEducationResponseDto.class)
                .collectList();
    }
}
