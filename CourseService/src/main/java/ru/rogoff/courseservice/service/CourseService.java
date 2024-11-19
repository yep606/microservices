package ru.rogoff.courseservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rogoff.courseservice.exception.NotFoundException;
import ru.rogoff.dto.CourseResponseDto;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    public List<CourseResponseDto> getByCvUuid(UUID cvUuid) {
        log.info("Mock call to Course database by cvUuid {}", cvUuid);
        if (cvUuid.toString().equals("8889cd30-831d-4247-a24a-0357f557c0fc")) {
            CourseResponseDto course1 = CourseResponseDto.builder()
                    .sequenceNumber(1)
                    .periodFrom(YearMonth.of(2020, 1))
                    .periodTo(YearMonth.of(2021, 6))
                    .presentTime(false)
                    .school("Online Platform A")
                    .courseName("Spring Boot Fundamentals")
                    .description("Learn the fundamentals of Spring Boot development.")
                    .certificateUrl("http://example.com/certificate1")
                    .build();

            CourseResponseDto course2 = CourseResponseDto.builder()
                    .sequenceNumber(2)
                    .periodFrom(YearMonth.of(2022, 7))
                    .periodTo(YearMonth.now())
                    .presentTime(true)
                    .school("Online Platform B")
                    .courseName("Reactive Programming with Spring")
                    .description("Deep dive into reactive programming concepts with Spring Framework.")
                    .certificateUrl("http://example.com/certificate2")
                    .build();

            return Arrays.asList(course1, course2);
        } else {
            throw new NotFoundException(String.format("Info by cv: %s not found", cvUuid));
        }
    }
}
