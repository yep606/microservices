package ru.javaguru.aggregator.service;

import by.javaguru.core.usecasses.entity.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.dto.DocumentRequestDto;
import ru.javaguru.aggregator.model.RequestStatus;
import ru.javaguru.aggregator.model.RequestTracking;
import ru.javaguru.aggregator.repo.RequestTrackingRepo;
import ru.javaguru.aggregator.utils.ServicesMapperUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final RequestTrackingRepo repo;
    private final DocumentProducer producer;
    private final AsyncTimeoutHandler handler;

    public void collectData(UUID reqUuid, DocumentRequestDto dto) {
        List<ServiceName> services = ServicesMapperUtils.mapToServices(dto.getType());

        RequestTracking tracking = new RequestTracking();
        tracking.setRequestId(reqUuid);
        tracking.setDocType(dto.getType());
        tracking.setExpectedServices(services);
        tracking.setStatus(RequestStatus.PENDING);

        repo.save(tracking);
        producer.send(reqUuid, dto);

        handler.handleTimeout(reqUuid, 15000); // 15s timeout
    }
}
