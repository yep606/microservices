package ru.javaguru.aggregator.processor;

import by.javaguru.core.usecasses.entity.ServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KafkaMessageProcessorRegistry {
    private final Map<ServiceName, KafkaMessageProcessor> processors = new HashMap<>();

    @Autowired
    public KafkaMessageProcessorRegistry(List<KafkaMessageProcessor> processors) {
        for (KafkaMessageProcessor processor : processors) {
            if (processor instanceof CoursesMessageProcessor) {
                this.processors.put(ServiceName.COURSE, processor);
            } else if (processor instanceof ExperienceMessageProcessor) {
                this.processors.put(ServiceName.EXPERIENCE, processor);
            }
        }
    }

    public KafkaMessageProcessor getProcessor(ServiceName name) {
        return processors.get(name);
    }
}
