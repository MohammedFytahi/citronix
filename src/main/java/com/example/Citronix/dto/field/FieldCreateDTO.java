package com.example.Citronix.dto.field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FieldCreateDTO {

    @NotBlank(message = "Field name is required.")
    private String name;

    @NotNull(message = "Field area is required.")
    @Positive(message = "Field area must be a positive number.")
    private Double area;

    @NotNull(message = "Farm ID is required.")
    private Long farmId;
}
