package com.example.Citronix.dto.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleUpdateDTO {

    private LocalDate saleDate;

    @Positive(message = "Le prix unitaire doit être un nombre positif.")
    private Double unitPrice;

    @NotBlank(message = "Le nom du client ne peut pas être vide.")
    private String clientName;
}
