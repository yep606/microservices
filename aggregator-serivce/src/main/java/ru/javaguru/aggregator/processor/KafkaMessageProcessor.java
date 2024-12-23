package ru.javaguru.aggregator.processor;

public interface KafkaMessageProcessor {
    void process(String message);
}
