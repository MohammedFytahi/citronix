package com.example.Citronix.dto.harvest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HarvestDetailCreateDTO {
    @NotNull
    private Long harvestId;
    @NotNull(message = "L'identifiant de l'arbre est obligatoire.")
    private Long treeId;

    @NotNull(message = "La quantité récoltée est obligatoire.")
    private Double quantity;
}
