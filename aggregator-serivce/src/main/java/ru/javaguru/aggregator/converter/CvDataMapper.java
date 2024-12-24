package ru.javaguru.aggregator.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javaguru.aggregator.dto.CvDataDto;
import ru.javaguru.aggregator.model.Course;
import ru.javaguru.aggregator.model.Experience;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, ExperienceMapper.class})
public interface CvDataMapper {

    @Mapping(source = "courses", target = "courses")
    @Mapping(source = "experiences", target = "experiences")
    CvDataDto toDto(List<Course> courses, List<Experience> experiences);
}
