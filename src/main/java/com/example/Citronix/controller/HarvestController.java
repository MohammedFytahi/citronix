package com.example.Citronix.controller;

import com.example.Citronix.dto.HarvestCreateDTO;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.service.HarvestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {

    @Autowired
    private HarvestService harvestService; // Injection automatique du service


    @PostMapping
    public ResponseEntity<Harvest> createHarvest(@Valid @RequestBody HarvestCreateDTO harvestCreateDTO) {
        Harvest harvest = harvestService.createHarvest(harvestCreateDTO);
        return ResponseEntity.ok(harvest);
    }
}
