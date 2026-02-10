package org.example.verificacion.service;

import java.util.List;
import org.example.verificacion.dto.CourseRequest;
import org.example.verificacion.model.Course;

public interface CourseService {

    Course create(CourseRequest request);

    List<Course> findAll();

    Course findById(Long id);

    Course update(Long id, CourseRequest request);

    void delete(Long id);
}
