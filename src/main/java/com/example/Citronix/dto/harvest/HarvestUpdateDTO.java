package com.example.Citronix.dto.harvest;
import com.example.Citronix.enums.Season;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HarvestUpdateDTO {

    @NotNull(message = "La saison ne peut pas être nulle.")
    private Season season;

    @NotNull(message = "La date de récolte ne peut pas être nulle.")
    @FutureOrPresent(message = "La date de récolte doit être aujourd'hui ou dans le futur.")
    private LocalDate harvestDate;
}
