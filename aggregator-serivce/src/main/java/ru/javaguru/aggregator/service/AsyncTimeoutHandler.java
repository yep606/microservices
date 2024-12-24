package ru.javaguru.aggregator.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.javaguru.aggregator.repo.RequestTrackingRepo;

import java.util.Optional;
import java.util.UUID;

@Component
public class AsyncTimeoutHandler {
    private final RequestTrackingRepo repo;
    private final AggregatorServiceCallback callback; // Handles sending aggregated data

    public AsyncTimeoutHandler(RequestTrackingRepository repository, AggregatorServiceCallback callback) {
        this.repository = repository;
        this.callback = callback;
    }

    @Async
    public void handleTimeout(UUID requestId, long timeoutMillis) {
        try {
            Thread.sleep(timeoutMillis); // Wait for timeout
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        // Fetch the tracking record after timeout
        Optional<RequestTracking> trackingOpt = repository.findById(requestId);
        if (trackingOpt.isPresent()) {
            RequestTracking tracking = trackingOpt.get();

            // Check if we received any responses
            if (tracking.getReceivedServices().isEmpty()) {
                tracking.setStatus(RequestStatus.FAILED);
            } else {
                tracking.setStatus(RequestStatus.COMPLETED);
            }

            // Save updated status
            repository.save(tracking);

            // Send the aggregated data using the callback
            callback.sendAggregatedData(tracking);
        }
    }
}
