package ru.rogoff.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Year;

@Value
@Builder
@Jacksonized
public class MainEducationResponseDto {
    Integer sequenceNumber;
    Year periodFrom;
    Year periodTo;
    Boolean presentTime;
    String institution;
    String faculty;
    String specialty;
}
