package ru.rogoff.maineducationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rogoff.dto.MainEducationResponseDto;
import ru.rogoff.maineducationservice.exception.NotFoundException;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainEducationService {

    public List<MainEducationResponseDto> getByCvUuid(UUID cvUuid) {
        log.info("Mock call to MainEducation database by cvUuid {}", cvUuid);
        if (cvUuid.toString().equals("8889cd30-831d-4247-a24a-0357f557c0fc")) {
            MainEducationResponseDto dto1 = MainEducationResponseDto.builder()
                    .sequenceNumber(1)
                    .periodFrom(Year.of(2010))
                    .periodTo(Year.of(2014))
                    .presentTime(false)
                    .institution("University A")
                    .faculty("Engineering")
                    .specialty("Computer Science")
                    .build();

            MainEducationResponseDto dto2 = MainEducationResponseDto.builder()
                    .sequenceNumber(2)
                    .periodFrom(Year.of(2015))
                    .periodTo(Year.of(2020))
                    .presentTime(true)
                    .institution("University B")
                    .faculty("Sciences")
                    .specialty("Physics")
                    .build();

            return List.of(dto1, dto2);
        } else {
            throw new NotFoundException(String.format("Info by cv: %s not found", cvUuid));
        }
    }
}
