package com.example.Citronix.dto.farm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarmSummaryDTO {
    private Long id;
    private String name;
    private String location;
}
