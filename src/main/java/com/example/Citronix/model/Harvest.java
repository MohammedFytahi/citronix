package com.example.Citronix.model;

import com.example.Citronix.enums.Season;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "harvests")
@Data
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Season season;
    @Column(name = "harvest_date", nullable = false)
    private LocalDate harvestDate;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HarvestDetail> harvestDetails = new ArrayList<>();


}
