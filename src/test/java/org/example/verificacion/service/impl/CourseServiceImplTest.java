package org.example.verificacion.service.impl;

import org.example.verificacion.exception.ResourceNotFoundException;
import org.example.verificacion.model.Course;
import org.example.verificacion.dto.CourseRequest;
import org.example.verificacion.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private CourseServiceImpl service;

    @Test
    void shouldCreateCourse() {
        CourseRequest request = new CourseRequest();
        setField(request, "name", "Java");
        setField(request, "description", "Curso Java");
        setField(request, "durationHours", 40);
        setField(request, "active", true);

        Course saved = new Course("Java", "Curso Java", 40, true);

        when(repository.save(any(Course.class))).thenReturn(saved);

        Course result = service.create(request);

        assertNotNull(result);
        verify(repository).save(any(Course.class));
    }

    @Test
    void shouldReturnAllCourses() {
        when(repository.findAll()).thenReturn(List.of());

        assertNotNull(service.findAll());
        verify(repository).findAll();
    }

    @Test
    void shouldFindCourseById() {
        Course course = new Course("Java", "Curso", 40, true);

        when(repository.findById(1L)).thenReturn(Optional.of(course));

        Course result = service.findById(1L);

        assertEquals("Java", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.findById(1L));
    }

    // Utilidad para setear DTO sin setters
    private static void setField(Object target, String field, Object value) {
        try {
            var f = target.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
