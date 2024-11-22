package com.example.Citronix.dto.harvest;

import com.example.Citronix.enums.Season;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HarvestDTO {

    private Long id;
    private Season season;
    private LocalDate harvestDate;
    private List<HarvestDetailDTO> harvestDetails;
}