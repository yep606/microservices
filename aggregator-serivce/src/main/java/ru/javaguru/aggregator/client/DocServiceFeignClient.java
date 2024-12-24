package ru.javaguru.aggregator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.javaguru.aggregator.dto.CvDataDto;

import java.util.UUID;

@FeignClient(name = "DOCUMENT-SERVICE")
public interface DocServiceFeignClient {
    @PostMapping("/documents/cv/{reqUuid}")
    ResponseEntity<Void> sendCvData(@PathVariable UUID reqUuid, @RequestBody CvDataDto cvDataDto);
}
