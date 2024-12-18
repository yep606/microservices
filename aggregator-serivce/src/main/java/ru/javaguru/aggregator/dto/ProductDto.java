package ru.javaguru.aggregator.dto;

import lombok.Data;
import ru.javaguru.aggregator.service.KafkaMessageProcessor;

@Data
public class ProductDto implements KafkaMessage {
    private String id;
    private String name;
    private String category;
    private double price;
    private boolean inStock;

    @Override
    public void acceptProcessor(KafkaMessageProcessor processor) {
        processor.process(this);
    }
}
