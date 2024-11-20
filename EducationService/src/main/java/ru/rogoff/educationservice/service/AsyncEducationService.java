package ru.rogoff.educationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.rogoff.dto.CourseResponseDto;
import ru.rogoff.dto.MainEducationResponseDto;
import ru.rogoff.educationservice.client.CoursesFeignClient;
import ru.rogoff.educationservice.client.MainEducationFeignClient;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Async
@Slf4j
public class AsyncEducationService {
    private final CoursesFeignClient coursesFeignClient;
    private final MainEducationFeignClient mainEducationFeignClient;

    public AsyncEducationService(CoursesFeignClient coursesFeignClient, MainEducationFeignClient mainEducationFeignClient) {
        this.coursesFeignClient = coursesFeignClient;
        this.mainEducationFeignClient = mainEducationFeignClient;
    }

    @Async
    public CompletableFuture<List<CourseResponseDto>> fetchCourses(UUID cvUuid) {
        sleep("courses");
        return CompletableFuture.supplyAsync(() -> coursesFeignClient.get(cvUuid));
    }

    @Async
    public CompletableFuture<List<MainEducationResponseDto>> fetchMainEducation(UUID cvUuid) {
        sleep("mainEducation");
        return CompletableFuture.supplyAsync(() -> mainEducationFeignClient.get(cvUuid));
    }

    private static void sleep(String method) {
        try {
            for (int i = 0; i < 2; i++) {
                Thread.sleep(1000);
                log.info("Sleep %d in %s".formatted(i, method));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
