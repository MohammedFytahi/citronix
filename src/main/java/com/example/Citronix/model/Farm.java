package com.example.Citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

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
}
