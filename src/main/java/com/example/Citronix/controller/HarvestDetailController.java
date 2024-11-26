package com.example.Citronix.controller;

import com.example.Citronix.dto.harvest.HarvestDetailCreateDTO;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.service.impl.HarvestDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvest-details")
public class HarvestDetailController {

    @Autowired
    private HarvestDetailService harvestDetailService;

    @GetMapping("/harvest/{harvestId}")
    public ResponseEntity<List<HarvestDetail>> getDetailsByHarvest(@PathVariable Long harvestId) {
        return ResponseEntity.ok(harvestDetailService.getDetailsByHarvest(harvestId));
    }

    @GetMapping("/tree/{treeId}")
    public ResponseEntity<List<HarvestDetail>> getDetailsByTree(@PathVariable Long treeId) {
        return ResponseEntity.ok(harvestDetailService.getDetailsByTree(treeId));
    }


}

