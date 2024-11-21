package com.example.Citronix.model;

import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.Tree;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "harvest_details")
public class HarvestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    @JsonBackReference
    private Harvest harvest;

    @ManyToOne
    @JoinColumn(name = "tree_id", nullable = false)
    @JsonBackReference
    private Tree tree;

    @Column(name = "quantity", nullable = false)
    private Double quantity;
}
