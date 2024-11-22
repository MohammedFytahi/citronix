package com.example.Citronix.dto.farm;

import com.example.Citronix.dto.field.FieldResponseDTO;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class FarmDTO {
    private Long id;
    private String name;
    private String location;
    private Double area;
    private LocalDate creationDate;
    private List<FieldResponseDTO> fields;




}
