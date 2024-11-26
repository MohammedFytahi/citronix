package com.example.Citronix.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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



    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Field> fields = new ArrayList<>();


    public Double calculateTotalFieldArea() {
        return fields.stream().mapToDouble(Field::getArea).sum();
    }

}
