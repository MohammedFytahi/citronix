package com.example.Citronix.dto.farm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FarmCreateDTO {

    @NotNull(message = "Le nom de la ferme est obligatoire.")
    private String name;

    private String location;

    @Positive(message = "La surface doit être un nombre positif.")
    private Double area;

    private LocalDate creationDate;



}
