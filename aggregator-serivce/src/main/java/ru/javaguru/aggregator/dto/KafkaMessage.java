package ru.javaguru.aggregator.dto;

import ru.javaguru.aggregator.service.KafkaMessageProcessor;

public interface KafkaMessage {

    void acceptProcessor(KafkaMessageProcessor processor);
}
