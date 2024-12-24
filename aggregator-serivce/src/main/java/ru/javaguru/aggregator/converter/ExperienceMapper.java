package ru.javaguru.aggregator.converter;

import by.javaguru.core.usecasses.dto.ExperienceRequestDto;
import by.javaguru.core.usecasses.dto.ExperienceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javaguru.aggregator.model.Experience;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    @Mapping(target = "reqUuid", ignore = true)
    ExperienceRequestDto toDto(Experience experience);

    @Mapping(target = "reqUuid", source = "reqUuid")
    Experience toEntity(ExperienceResponseDto experienceDto, UUID reqUuid);

    @Mapping(target = "reqUuid", ignore = true)
    List<ExperienceResponseDto> toDtoList(List<Experience> experiences);

    default List<Experience> toEntityList(List<ExperienceResponseDto> dtos, UUID reqUuid) {
        return dtos.stream()
                .map(expDto -> toEntity(expDto, reqUuid))
                .toList();
    }
}
