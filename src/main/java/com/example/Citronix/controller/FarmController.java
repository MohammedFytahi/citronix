package com.example.Citronix.controller;

import com.example.Citronix.dto.FarmCreateDTO;
import com.example.Citronix.service.impl.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

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
}
