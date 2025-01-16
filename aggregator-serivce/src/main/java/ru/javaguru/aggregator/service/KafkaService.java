package ru.javaguru.aggregator.service;

import by.javaguru.core.usecasses.entity.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.exception.RequestTrackingCheckException;
import ru.javaguru.aggregator.exception.RequestTrackingNotFoundException;
import ru.javaguru.aggregator.model.RequestTracking;
import ru.javaguru.aggregator.processor.KafkaMessageProcessor;
import ru.javaguru.aggregator.processor.KafkaMessageProcessorRegistry;
import ru.javaguru.aggregator.repo.RequestTrackingRepo;

import java.util.UUID;

import static ru.javaguru.aggregator.model.RequestStatus.COMPLETED;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {
    private static final String TOPIC = "doc.requests";
    private static final String GROUP_ID = "aggregator-group";

    private final RequestTrackingRepo repo;
    private final KafkaMessageProcessorRegistry registry;
    private final AggregatorService aggregator;

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(@Header("reqUuid") UUID reqUuid,
                        @Header("serviceName") ServiceName serviceName,
                        String message) {
        processMessage(reqUuid, serviceName, message);
    }

    private void processMessage(UUID reqUuid, ServiceName serviceName, String message) {
        RequestTracking requestTracking = repo.findById(reqUuid)
                .orElseThrow(() -> new RequestTrackingNotFoundException("RequestTracking with reqUuid: %s not found".formatted(reqUuid)));
        checkRequestTracking(requestTracking, serviceName);

        // process and save incoming message with reqUuid for future use and info sending back to document service
        KafkaMessageProcessor processor = registry.getProcessor(serviceName);
        processor.process(reqUuid, message);

        requestTracking.getReceivedServices().add(serviceName);
        repo.save(requestTracking);

        postProcessRequestTracking(requestTracking);
    }

    //todo: check CANCELLED OR COMPLETED status by timeoutHandler, important case
    private void postProcessRequestTracking(RequestTracking tracking) {
        if (tracking.getExpectedServices().size() == tracking.getReceivedServices().size()) {
            tracking.setStatus(COMPLETED);
            repo.save(tracking);

            // retrieve needed data previously saved from db and send back to doc service
            aggregator.aggregateAndSend(tracking.getRequestId(), tracking.getDocType());
        }
    }

    void checkRequestTracking(RequestTracking requestTracking, ServiceName serviceName) {
        if (COMPLETED.equals(requestTracking.getStatus())
                        || !requestTracking.getExpectedServices().contains(serviceName)
                || requestTracking.getReceivedServices().contains(serviceName)) {
            throw new RequestTrackingCheckException("Check failed");
        }
    }

}
