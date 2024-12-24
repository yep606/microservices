package ru.javaguru.aggregator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javaguru.aggregator.client.DocServiceFeignClient;
import ru.javaguru.aggregator.converter.CvDataMapper;
import ru.javaguru.aggregator.dto.CvDataDto;
import ru.javaguru.aggregator.dto.DocType;
import ru.javaguru.aggregator.model.Course;
import ru.javaguru.aggregator.model.Experience;
import ru.javaguru.aggregator.repo.CourseRepo;
import ru.javaguru.aggregator.repo.ExperienceRepo;

import java.util.List;
import java.util.UUID;

import static ru.javaguru.aggregator.dto.DocType.CV;

@Service
@RequiredArgsConstructor
public class AggregatorService {

    private final CvDataMapper mapper;
    private final CourseRepo courseRepo;
    private final ExperienceRepo expRepo;
    private final DocServiceFeignClient client;

    //todo: хардкодим docType CV - в дальнейшем сделать динамическую стратегию в зависимости от типа
    public void aggregateAndSend(UUID reqUuid, DocType docType) {
        if (CV.equals(docType)) {
            List<Course> courses = courseRepo.findAllByReqUuid(reqUuid);
            List<Experience> experiences = expRepo.findAllByReqUuid(reqUuid);
            CvDataDto result = mapper.toDto(courses, experiences);

            client.sendCvData(reqUuid, result);
        }
    }
}
