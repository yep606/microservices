package ru.rogoff.educationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.rogoff.dto.MainEducationResponseDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "MAIN-EDUCATION-SERVICE")
public interface MainEducationFeignClient {
    @GetMapping("/main-education/{cvUuid}")
    List<MainEducationResponseDto> get(@PathVariable UUID cvUuid);
}
