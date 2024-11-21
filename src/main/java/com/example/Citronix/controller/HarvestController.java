package com.example.Citronix.controller;

import com.example.Citronix.dto.HarvestCreateDTO;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.service.impl.HarvestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @PostMapping("/field/{fieldId}")
    public ResponseEntity<?> createHarvest(
            @PathVariable Long fieldId,
            @Valid @RequestBody HarvestCreateDTO harvestCreateDTO) {
        try {
            Harvest harvest = harvestService.createHarvest(harvestCreateDTO, fieldId);
            return ResponseEntity.ok(harvest); // Retourne la récolte avec ses détails
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
