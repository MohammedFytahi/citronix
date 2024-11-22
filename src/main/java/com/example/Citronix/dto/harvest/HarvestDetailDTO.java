package com.example.Citronix.dto.harvest;

import lombok.Data;

@Data
public class HarvestDetailDTO {
    private Long id;
    private Long treeId;
    private Double quantity;
}
