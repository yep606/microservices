package ru.javaguru.aggregator.service;

import ru.javaguru.aggregator.dto.OrderDto;
import ru.javaguru.aggregator.dto.ProductDto;

public interface KafkaMessageProcessor {
    void process(OrderDto order);
    void process(ProductDto order);
}
