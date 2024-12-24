package ru.javaguru.aggregator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaguru.aggregator.model.Experience;

import java.util.List;
import java.util.UUID;

public interface ExperienceRepo extends JpaRepository<Experience, Long> {
    List<Experience> findAllByReqUuid(UUID reqUuid);
}
