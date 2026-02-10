package org.example.verificacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private Integer durationHours;

    @Column(nullable = false)
    private Boolean active;

    protected Course() {
        // Constructor requerido por JPA
    }

    public Course(final String name,
                  final String description,
                  final Integer durationHours,
                  final Boolean active) {
        this.name = name;
        this.description = description;
        this.durationHours = durationHours;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

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

    public void update(final String name,
                       final String description,
                       final Integer durationHours,
                       final Boolean active) {
        this.name = name;
        this.description = description;
        this.durationHours = durationHours;
        this.active = active;
    }
}
