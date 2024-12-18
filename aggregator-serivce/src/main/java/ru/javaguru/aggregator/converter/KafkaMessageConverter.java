package ru.javaguru.aggregator.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javaguru.aggregator.dto.KafkaMessage;
import ru.javaguru.aggregator.dto.OrderDto;
import ru.javaguru.aggregator.dto.ProductDto;

import java.util.Map;

@Component
public class KafkaMessageConverter {
    private final ObjectMapper objectMapper;
    private final Map<String, Class<?>> dtoMapping = Map.of(
            "order-service", OrderDto.class,
            "product-service", ProductDto.class
    );

    @Autowired
    public KafkaMessageConverter(ObjectMapper objectMapper) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper = objectMapper;
    }

    public KafkaMessage convert(String serviceName, String message) throws JsonProcessingException {
        Class<?> targetType = dtoMapping.get(serviceName);
        return (KafkaMessage) objectMapper.readValue(message, targetType);
    }
}
