package ru.javaguru.aggregator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.processor.KafkaMessageProcessor;
import ru.javaguru.aggregator.processor.KafkaMessageProcessorRegistry;


@Slf4j
@Service
public class KafkaService {
    private final KafkaMessageProcessorRegistry registry;

    @Autowired
    public KafkaService(KafkaMessageProcessorRegistry registry) {
        this.registry = registry;
    }

    @KafkaListener(topics = "doc-info", groupId = "aggregator-group")
    public void consume(@Header("serviceName") String serviceName, String message) {
        processMessage(serviceName, message);
    }

    private void processMessage(String serviceName, String message) {
        KafkaMessageProcessor processor = registry.getProcessor(serviceName);
        processor.process(message);
    }
}
