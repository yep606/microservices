package ru.javaguru.aggregator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.javaguru.aggregator.exception.RequestTrackingNotFoundException;
import ru.javaguru.aggregator.model.RequestTracking;
import ru.javaguru.aggregator.repo.RequestTrackingRepo;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static ru.javaguru.aggregator.model.RequestStatus.COMPLETED;
import static ru.javaguru.aggregator.model.RequestStatus.FAILED;
import static ru.javaguru.aggregator.model.RequestStatus.PENDING;

/**
 * Simple timeout handler for Doc requests.
 * If timout was exceeded and conditions are not met, change status to FAILED and send previously received data.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AsyncTimeoutHandler {

    private final RequestTrackingRepo repo;
    private final AggregatorService service;

    @Async
    public void handleTimeout(UUID reqUuid, long timeoutMillis) {
        CompletableFuture
                .runAsync(() -> processAfterDelay(reqUuid),
                        CompletableFuture.delayedExecutor(timeoutMillis, TimeUnit.MILLISECONDS));
    }

    private void processAfterDelay(UUID reqUuid) {
        RequestTracking tracking = getRequestTracking(reqUuid);
        if (PENDING.equals(tracking.getStatus())) {
            updateTrackingStatus(tracking);
            service.aggregateAndSend(reqUuid, tracking.getDocType());
        }
    }

    private RequestTracking getRequestTracking(UUID reqUuid) {
        return repo.findById(reqUuid)
                .orElseThrow(() -> new RequestTrackingNotFoundException("RequestTracking with reqUuid: %s not found".formatted(reqUuid)));
    }

    private void updateTrackingStatus(RequestTracking tracking) {
        if (tracking.getReceivedServices().size() < tracking.getExpectedServices().size()) {
            tracking.setStatus(FAILED);
        } else {
            tracking.setStatus(COMPLETED);
        }
        repo.save(tracking);
    }
}
