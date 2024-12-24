package ru.javaguru.aggregator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaguru.aggregator.model.RequestTracking;

import java.util.UUID;

public interface RequestTrackingRepo extends JpaRepository<RequestTracking, UUID> {
}
