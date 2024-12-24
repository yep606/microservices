package ru.javaguru.aggregator.processor;

import by.javaguru.core.usecasses.dto.CourseResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.converter.CourseMapper;
import ru.javaguru.aggregator.converter.KafkaMessageConverter;
import ru.javaguru.aggregator.model.Course;
import ru.javaguru.aggregator.repo.CourseRepo;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoursesMessageProcessor implements KafkaMessageProcessor {

    private final CourseMapper mapper;
    private final CourseRepo repo;
    private final KafkaMessageConverter<CourseResponseDto> messageConverter;

    //todo: implement entity batch saving
    @Override
    public void process(UUID reqUuid, String message) {
        try {
            List<CourseResponseDto> dtos = messageConverter.convertList(message, new TypeReference<>() {
            });

            List<Course> courses = mapper.toEntityList(dtos, reqUuid);
            log.info("Map to Courses entity: {}", courses);

            log.info("Save to DB...");
            repo.saveAll(courses);

        } catch (JsonProcessingException ex) {
            log.error("Error occurred: {}", ex.getMessage());
        }
    }
}
