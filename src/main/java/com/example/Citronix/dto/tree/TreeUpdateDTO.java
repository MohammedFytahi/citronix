package com.example.Citronix.dto.tree;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeUpdateDTO {



    private LocalDate plantingDate;

    private Long fieldId;
}
