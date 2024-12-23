package ru.javaguru.aggregator.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KafkaMessageProcessorRegistry {
    private final Map<String, KafkaMessageProcessor> processors = new HashMap<>();

    @Autowired
    public KafkaMessageProcessorRegistry(List<KafkaMessageProcessor> processors) {
        for (KafkaMessageProcessor processor : processors) {
            if (processor instanceof CoursesMessageProcessor) {
                this.processors.put("courses-service", processor);
            } else if (processor instanceof ExperienceMessageProcessor) {
                this.processors.put("experience-service", processor);
            }
        }
    }

    public KafkaMessageProcessor getProcessor(String serviceName) {
        return processors.get(serviceName);
    }
}
