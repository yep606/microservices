package ru.javaguru.aggregator.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.converter.KafkaMessageConverter;
import ru.javaguru.aggregator.converter.OrderMapper;
import ru.javaguru.aggregator.model.Course;

import java.util.List;

@Slf4j
@Service
public class CoursesMessageProcessor implements KafkaMessageProcessor {

    private final OrderMapper mapper;
    private final KafkaMessageConverter<Course> messageConverter;
    @Autowired
    public CoursesMessageProcessor(OrderMapper mapper, KafkaMessageConverter<Course> messageConverter) {
        this.mapper = mapper;
        this.messageConverter = messageConverter;
    }
    @Override
    public void process(String message) {
        try {
            List<Course> courses = messageConverter.convertList(message, new TypeReference<>() {});
            log.info("Map to Courses entity: {}", courses);
            log.info("Save to DB...");
        } catch (JsonProcessingException ex) {
            log.error("Error occurred: {}", ex.getMessage());
        }
    }

}
