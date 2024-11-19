package ru.rogoff.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class EducationResponseDto {
    List<CourseResponseDto> courses;
    List<MainEducationResponseDto> mainEducations;
}