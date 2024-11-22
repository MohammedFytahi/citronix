package com.example.Citronix.dto.field;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldUpdateDTO {
    private Long id;
    private String name;
    private Double area;
    private Long farmId;

}
