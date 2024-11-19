package com.example.Citronix.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "farms")

public class Farm {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(name = "name", nullable = false)
    private String name;
@Column(name = "location")
private String location;

@Column(name = "area")
    private Double area;
@Column(name = "creation_date")
private LocalDate creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
