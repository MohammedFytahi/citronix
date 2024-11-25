package com.example.Citronix.dto.farm;

import lombok.Data;

@Data
public class FarmSearchCriteria {
    private String name;          // Nom de la ferme
    private String location;      // Localisation
    private Double minArea;       // Superficie minimale
    private Double maxArea;       // Superficie maximale
}
