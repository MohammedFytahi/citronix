package com.example.Citronix.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class FarmDTO {
    private Long id;
    private String name;
    private String location;
    private Double area;
    private LocalDate creationDate;




}
