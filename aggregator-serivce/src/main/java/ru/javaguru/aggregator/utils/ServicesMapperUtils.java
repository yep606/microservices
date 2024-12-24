package ru.javaguru.aggregator.utils;

import by.javaguru.core.usecasses.entity.ServiceName;
import lombok.experimental.UtilityClass;
import ru.javaguru.aggregator.dto.DocType;

import java.util.List;
import java.util.Map;

import static ru.javaguru.aggregator.dto.DocType.CV;
import static by.javaguru.core.usecasses.entity.ServiceName.COURSE;
import static by.javaguru.core.usecasses.entity.ServiceName.EXPERIENCE;

@UtilityClass
public class ServicesMapperUtils {
    private final static Map<DocType, List<ServiceName>> docServices = Map.of(
            CV, List.of(COURSE, EXPERIENCE)
    );

    public static List<ServiceName> mapToServices(DocType type) {
        return docServices.get(type);
    }
}
