package ru.javaguru.aggregator.processor;

import by.javaguru.core.usecasses.dto.CourseResponseDto;
import by.javaguru.core.usecasses.dto.ExperienceResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.converter.CourseMapper;
import ru.javaguru.aggregator.converter.ExperienceMapper;
import ru.javaguru.aggregator.converter.KafkaMessageConverter;
import ru.javaguru.aggregator.model.Course;
import ru.javaguru.aggregator.model.Experience;
import ru.javaguru.aggregator.repo.CourseRepo;
import ru.javaguru.aggregator.repo.ExperienceRepo;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExperienceMessageProcessor implements KafkaMessageProcessor {

    private final ExperienceMapper mapper;
    private final ExperienceRepo repo;
    private final KafkaMessageConverter<ExperienceResponseDto> messageConverter;

    //todo: implement entity batch saving
    @Override
    public void process(UUID reqUuid, String message) {
        try {
            List<ExperienceResponseDto> dtos = messageConverter.convertList(message, new TypeReference<>() {
            });

            List<Experience> experiences = mapper.toEntityList(dtos, reqUuid);
            log.info("Map to Experiences entity: {}", experiences);

            log.info("Save to DB...");
            repo.saveAll(experiences);
        } catch (JsonProcessingException ex) {
            log.error("Error occurred: {}", ex.getMessage());
        }
    }
}
