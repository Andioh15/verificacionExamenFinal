package org.example.verificacion.controller;

import org.example.verificacion.model.Course;
import org.example.verificacion.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService service;

    @Test
    void shouldReturnListOfCourses() throws Exception {
        when(service.findAll()).thenReturn(
                List.of(new Course("Java", "Curso", 40, true))
        );

        mockMvc.perform(get("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Java"));
    }

    @Test
    void shouldReturnCourseById() throws Exception {
        when(service.findById(1L))
                .thenReturn(new Course("Java", "Curso", 40, true));

        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Java"));
    }
}
