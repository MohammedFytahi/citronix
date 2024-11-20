package com.example.Citronix.controller;

import com.example.Citronix.dto.FarmCreateDTO;
import com.example.Citronix.dto.FarmDTO;
import com.example.Citronix.dto.FarmUpdateDTO;
import com.example.Citronix.service.impl.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {
    @Autowired
    private FarmService farmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addFarm(@Valid @RequestBody FarmCreateDTO farmCreateDTO) {
        farmService.addFarm(farmCreateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFarm(@PathVariable Long id, @RequestBody FarmUpdateDTO farmUpdateDTO) {
        farmService.updateFarm(id, farmUpdateDTO);
        return ResponseEntity.ok("Farm updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.ok("Farm deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<FarmDTO>> getAllFarms() {
        List<FarmDTO> farms = farmService.getAllFarms();
        return ResponseEntity.ok(farms);
    }
}
