package com.example.Citronix.dto;

import com.example.Citronix.enums.Season;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SaleDTO {
    private Long id;
    private LocalDate saleDate;
    private Double unitPrice;
    private Double quantity;
    private String clientName;
    private Double revenue;

    private Long harvestId;
    private Season harvestSeason;
    private LocalDate harvestDate;
}
