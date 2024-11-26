package com.example.Citronix.dto.farm;

import lombok.Data;

@Data
public class FarmSearchCriteria {
    private String name;
    private String location;
    private Double minArea;
    private Double maxArea;
}
