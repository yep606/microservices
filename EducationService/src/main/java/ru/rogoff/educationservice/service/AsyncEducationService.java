package ru.rogoff.educationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rogoff.dto.CourseResponseDto;
import ru.rogoff.dto.MainEducationResponseDto;
import ru.rogoff.educationservice.client.CoursesFeignClient;
import ru.rogoff.educationservice.client.MainEducationFeignClient;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AsyncEducationService {
    private final CoursesFeignClient coursesFeignClient;
    private final MainEducationFeignClient mainEducationFeignClient;

    public AsyncEducationService(CoursesFeignClient coursesFeignClient, MainEducationFeignClient mainEducationFeignClient) {
        this.coursesFeignClient = coursesFeignClient;
        this.mainEducationFeignClient = mainEducationFeignClient;
    }

    public List<CourseResponseDto> fetchCourses(UUID cvUuid) {
        return coursesFeignClient.get(cvUuid);
    }

    public List<MainEducationResponseDto> fetchMainEducation(UUID cvUuid) {
        return mainEducationFeignClient.get(cvUuid);
    }
}
