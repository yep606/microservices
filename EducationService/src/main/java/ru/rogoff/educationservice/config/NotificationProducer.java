package ru.rogoff.educationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.rogoff.kafka.NotificationEvent;

@Service
public class NotificationProducer {

    private static final String TOPIC = "notification-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public NotificationProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(NotificationEvent event) {
        kafkaTemplate.send(TOPIC, event.getId(), event);
    }
}
