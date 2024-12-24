package ru.javaguru.aggregator.dto;

import by.javaguru.core.usecasses.dto.CourseResponseDto;
import by.javaguru.core.usecasses.dto.ExperienceResponseDto;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class CvDataDto {
    List<CourseResponseDto> courses;
    List<ExperienceResponseDto> experiences;
}
