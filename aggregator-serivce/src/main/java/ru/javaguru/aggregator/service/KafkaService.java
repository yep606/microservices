package ru.javaguru.aggregator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.converter.KafkaMessageConverter;
import ru.javaguru.aggregator.dto.KafkaMessage;


@Slf4j
@Service
public class KafkaService {

    private final KafkaMessageConverter converter;
    private final KafkaMessageProcessor processor;

    @Autowired
    public KafkaService(KafkaMessageConverter converter, KafkaMessageProcessor processor) {
        this.converter = converter;
        this.processor = processor;
    }

    @KafkaListener(topics = "doc-info", groupId = "aggregator-group")
    public void consume(@Header("serviceName") String serviceName, String message) {
        processMessage(serviceName, message);
    }

    private void processMessage(String serviceName, String kafkaMessage) {
        try {
            KafkaMessage message = converter.convert(serviceName, kafkaMessage);
            message.acceptProcessor(processor);

        } catch (JsonProcessingException ex) {
            logError(ex);
        }
    }


    private void logError(Exception ex) {
        log.info("Exception occurred while process kafka message: {}", ex);
    }
}
