package ru.javaguru.aggregator.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.dto.DocumentRequestDto;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class DocumentProducer {
    private static final String TOPIC = "notification-events";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public DocumentProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UUID reqUuid, DocumentRequestDto message) {
        ProducerRecord<Object, DocumentRequestDto> record = new ProducerRecord<>(TOPIC, message);
        Header reqUuidHeader = new RecordHeader("reqUuid", reqUuid.toString().getBytes(StandardCharsets.UTF_8));
        record.headers().add(reqUuidHeader);

        kafkaTemplate.send(TOPIC, record);
    }
}
