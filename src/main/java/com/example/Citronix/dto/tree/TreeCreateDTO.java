package com.example.Citronix.dto.tree;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeCreateDTO {

    @NotNull(message = "La date de plantation est obligatoire.")
    private LocalDate plantingDate;

    @NotNull(message = "L'ID du champ est obligatoire.")
    private Long fieldId;
}
