package ru.javaguru.aggregator.processor;

import java.util.UUID;

public interface KafkaMessageProcessor {
    void process(UUID reqUuid, String message);
}
