package com.example.Citronix.dto.field;

import com.example.Citronix.dto.farm.FarmSummaryDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldDTO {
    private Long id;
    private String name;
    private Double area;
    private FarmSummaryDTO farm;

}
