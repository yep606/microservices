package ru.rogoff.educationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.rogoff.dto.EducationResponseDto;
import ru.rogoff.educationservice.client.CoursesClient;
import ru.rogoff.educationservice.client.MainEducationClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final CoursesClient coursesClient;
    private final MainEducationClient mainEducationClient;

    public Mono<EducationResponseDto> getEducation(UUID cvUuid) {
        return Mono.zip(
                        coursesClient.getCourses(cvUuid),
                        mainEducationClient.getMainEducation(cvUuid))
                .map(tuple ->
                        EducationResponseDto.builder()
                                .courses(tuple.getT1())
                                .mainEducations(tuple.getT2())
                                .build());
    }
}
