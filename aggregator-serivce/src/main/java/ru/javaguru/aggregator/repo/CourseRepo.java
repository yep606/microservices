package ru.javaguru.aggregator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaguru.aggregator.model.Course;

import java.util.List;
import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findAllByReqUuid(UUID reqUuid);
}
