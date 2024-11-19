package ru.rogoff.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.YearMonth;

@Value
@Builder
@Jacksonized
public class CourseResponseDto {
    Integer sequenceNumber;
    YearMonth periodFrom;
    YearMonth periodTo;
    Boolean presentTime;
    String school;
    String courseName;
    String description;
    String certificateUrl;
}
