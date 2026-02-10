package org.example.verificacion.repository;

import org.example.verificacion.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void shouldSaveCourse() {
        Course course = new Course(
                "Java",
                "Curso Java",
                40,
                true
        );

        Course saved = repository.save(course);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Java");
    }

    @Test
    void shouldFindById() {
        Course course = repository.save(
                new Course("Spring", "Curso Spring", 30, true)
        );

        Optional<Course> result = repository.findById(course.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Spring");
    }

    @Test
    void shouldFindAllCourses() {
        repository.save(new Course("Java", "Curso Java", 40, true));
        repository.save(new Course("Spring", "Curso Spring", 30, true));

        List<Course> courses = repository.findAll();

        assertThat(courses).hasSize(2);
    }

    @Test
    void shouldDeleteCourse() {
        Course course = repository.save(
                new Course("Delete", "Curso Delete", 20, true)
        );

        repository.deleteById(course.getId());

        assertThat(repository.findById(course.getId())).isEmpty();
    }
}
