package ru.javaguru.aggregator.dto;

import lombok.Data;
import ru.javaguru.aggregator.service.KafkaMessageProcessor;

@Data
public class OrderDto implements KafkaMessage {
    private String description;
    private String phoneNumber;
    private String status;

    @Override
    public void acceptProcessor(KafkaMessageProcessor processor) {
        processor.process(this);
    }
}