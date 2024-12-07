package org.example.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.rogoff.kafka.NotificationEvent;

@Slf4j
@Service
public class KafkaService {
    @KafkaListener(topics = "notification-events", groupId = "notification-service")
    public void consume(NotificationEvent event) {
        log.info("Incoming event {}", event);
//        Notification notification = convertToEntity(event);
//        synchronized (batchBuffer) {
//            batchBuffer.add(notification);
//            if (batchBuffer.size() >= BATCH_SIZE) {
//                flushBatch();
//            }
    }
}
