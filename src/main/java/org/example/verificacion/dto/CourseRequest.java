package org.example.verificacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CourseRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotNull
    @Positive
    private Integer durationHours;

    @NotNull
    private Boolean active;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public Boolean getActive() {
        return active;
    }
}
