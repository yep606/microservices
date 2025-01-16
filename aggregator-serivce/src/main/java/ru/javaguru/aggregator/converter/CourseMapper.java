package ru.javaguru.aggregator.converter;

import by.javaguru.core.usecasses.dto.CourseResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javaguru.aggregator.model.Course;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponseDto toDto(Course course);

    @Mapping(target = "reqUuid", source = "reqUuid")
    Course toEntity(CourseResponseDto courseDto, UUID reqUuid);

    @Mapping(target = "reqUuid", ignore = true)
    List<CourseResponseDto> toDtoList(List<Course> courses);

    default List<Course> toEntityList(List<CourseResponseDto> dtos, UUID reqUuid) {
        return dtos.stream()
                .map(courseDto -> toEntity(courseDto, reqUuid))
                .toList();
    }
}
