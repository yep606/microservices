package ru.javaguru.aggregator.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.converter.KafkaMessageConverter;
import ru.javaguru.aggregator.converter.OrderMapper;
import ru.javaguru.aggregator.model.Experience;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ExperienceMessageProcessor implements KafkaMessageProcessor {

    private final OrderMapper mapper;
    private final KafkaMessageConverter<Experience> messageConverter;
    @Autowired
    public ExperienceMessageProcessor(OrderMapper mapper, KafkaMessageConverter<Experience> messageConverter) {
        this.mapper = mapper;
        this.messageConverter = messageConverter;
    }
    @Override
    public void process(UUID reqUuid, String message) {
        try {
            List<Experience> experiences = messageConverter.convertList(message, new TypeReference<>() {});
            log.info("Map to Experiences entity: {}", experiences);
            log.info("Save to DB...");
        } catch (JsonProcessingException ex) {
            log.error("Error occurred: {}", ex.getMessage());
        }
    }
}
