package com.example.Citronix.controller;

import com.example.Citronix.dto.harvest.HarvestCreateDTO;
import com.example.Citronix.dto.harvest.HarvestDTO;
import com.example.Citronix.dto.harvest.HarvestUpdateDTO;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.service.impl.HarvestService;
import com.example.Citronix.service.interf.HarvestServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestServiceInterface harvestService;

    @PostMapping("/field/{fieldId}")
    public ResponseEntity<?> createHarvest(
            @PathVariable Long fieldId,
            @Valid @RequestBody HarvestCreateDTO harvestCreateDTO) {
        try {
            Harvest harvest = harvestService.createHarvest(harvestCreateDTO, fieldId);
            return ResponseEntity.ok(harvest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Harvest> updateHarvest(
            @PathVariable Long id,
            @Valid @RequestBody HarvestUpdateDTO harvestUpdateDTO) {
        Harvest updatedHarvest = harvestService.updateHarvest(id, harvestUpdateDTO);
        return ResponseEntity.ok(updatedHarvest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHarvest(@PathVariable Long id) {
        try {
            harvestService.deleteHarvest(id);
            return ResponseEntity.ok("Harvest deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDTO> getHarvestById(@PathVariable Long id) {
        HarvestDTO harvestDTO = harvestService.getHarvestById(id);
        return ResponseEntity.ok(harvestDTO);
    }
}
