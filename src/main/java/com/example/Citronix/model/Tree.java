package com.example.Citronix.model;

import com.example.Citronix.model.Field;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate plantingDate;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;


}
