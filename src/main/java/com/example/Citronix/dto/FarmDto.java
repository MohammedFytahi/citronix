package com.example.Citronix.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FarmDto {
    private Long id;

    @NotBlank(message = "le nom de la ferme est obligatoire")
    private String name;
@NotBlank(message = "la localisation de la ferme est obligatoire")
    private String location;
    private Double area;
    private LocalDate creationDate;
}
