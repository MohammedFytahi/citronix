package com.example.Citronix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleCreateDTO {

    @NotNull(message = "La date de vente est obligatoire.")
    private LocalDate saleDate;

    @NotNull(message = "Le prix unitaire est obligatoire.")
    @Positive(message = "Le prix unitaire doit être un nombre positif.")
    private Double unitPrice;

    @NotNull(message = "La quantité est obligatoire.")
    @Positive(message = "La quantité doit être un nombre positif.")
    private Double quantity;

    @NotBlank(message = "Le nom du client est obligatoire.")
    private String clientName;

    @NotNull(message = "L'ID de la récolte est obligatoire.")
    private Long harvestId;
}
