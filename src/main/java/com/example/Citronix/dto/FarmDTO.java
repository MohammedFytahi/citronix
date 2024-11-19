package com.example.Citronix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmDTO {
    private Long id;

    @NotBlank(message = "le nom de la ferme est obligatoire")
    private String name;
@NotBlank(message = "la localisation de la ferme est obligatoire")
    private String location;
@NotNull(message =  "La superficie de la ferme est obligatoire.")
@Positive(message = "La superficie doit être positive.")
    private Double area;
    @NotNull(message = "La date de création est obligatoire.")
    @PastOrPresent(message = "La date de création ne peut pas être dans le futur.")
    private LocalDate creationDate;
}
