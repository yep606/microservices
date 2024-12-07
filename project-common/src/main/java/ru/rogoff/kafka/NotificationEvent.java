package ru.rogoff.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEvent {
    private String id;
    private String message;
    private EventType type;

    @Override
    public String toString() {
        return "NotificationEvent{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}
