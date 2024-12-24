package ru.javaguru.aggregator.model;

import by.javaguru.core.usecasses.entity.ServiceName;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javaguru.aggregator.dto.DocType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RequestTracking {

    @Id
    private UUID requestId;

    private DocType docType;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ServiceName> expectedServices = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ServiceName> receivedServices = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
