package ru.rogoff.educationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.rogoff.dto.CourseResponseDto;
import ru.rogoff.dto.EducationResponseDto;
import ru.rogoff.dto.MainEducationResponseDto;
import ru.rogoff.educationservice.client.CoursesClient;
import ru.rogoff.educationservice.client.MainEducationClient;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class EducationServiceImpl {

    private final CoursesClient coursesClient;
    private final MainEducationClient mainEducationClient;
    private final AsyncEducationService service;

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
    @SneakyThrows
    public EducationResponseDto getAsyncEducation(UUID cvUuid) {
        CompletableFuture<List<CourseResponseDto>> coursesFuture = service.fetchCourses(cvUuid);
        log.info("Async call");
        CompletableFuture<List<MainEducationResponseDto>> mainEducationFuture = service.fetchMainEducation(cvUuid);

        CompletableFuture.allOf(coursesFuture, mainEducationFuture).join();

        List<CourseResponseDto> courses = coursesFuture.get();
        List<MainEducationResponseDto> mainEducation = mainEducationFuture.get();

        return EducationResponseDto.builder()
                .courses(courses)
                .mainEducations(mainEducation)
                .build();
    }
}
