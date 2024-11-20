package com.example.Citronix.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "area", nullable = false)
    private Double area;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;
}