package org.example.verificacion.service.impl;

import java.util.List;
import org.example.verificacion.dto.CourseRequest;
import org.example.verificacion.exception.ResourceNotFoundException;
import org.example.verificacion.model.Course;
import org.example.verificacion.repository.CourseRepository;
import org.example.verificacion.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(final CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Course create(final CourseRequest request) {
        final Course course = new Course(
                request.getName(),
                request.getDescription(),
                request.getDurationHours(),
                request.getActive()
        );
        return repository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Course findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public Course update(final Long id, final CourseRequest request) {
        final Course course = findById(id);
        course.update(
                request.getName(),
                request.getDescription(),
                request.getDurationHours(),
                request.getActive()
        );
        return repository.save(course);
    }

    @Override
    public void delete(final Long id) {
        final Course course = findById(id);
        repository.delete(course);
    }
}
