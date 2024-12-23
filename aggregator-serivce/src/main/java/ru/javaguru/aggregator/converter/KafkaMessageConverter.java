package ru.javaguru.aggregator.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaMessageConverter<T> {
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaMessageConverter(ObjectMapper objectMapper) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper = objectMapper;
    }

    public T convert(String message, Class<T> type) throws JsonProcessingException {
        return objectMapper.readValue(message, type);
    }

    public List<T> convertList(String message, TypeReference<List<T>> typeReference) throws JsonProcessingException {
        return objectMapper.readValue(message, typeReference);
    }
}
