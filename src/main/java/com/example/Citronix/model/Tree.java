package com.example.Citronix.model;

import com.example.Citronix.enums.Season;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Data
@Table(name = "trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planting_date", nullable = false)
    private LocalDate plantingDate;

    @Transient
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @JsonBackReference
    private Field field;

    public Integer calculateAge() {
        if (plantingDate == null) return 0;
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }
    @OneToMany(mappedBy = "tree")
    private List<HarvestDetail> harvestDetails;


    public double calculateAnnualProductivity() {
        int age = calculateAge();
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else {
            return 20.0;
        }
    }
}
