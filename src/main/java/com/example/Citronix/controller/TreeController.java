package com.example.Citronix.controller;

import com.example.Citronix.dto.tree.TreeCreateDTO;
import com.example.Citronix.dto.tree.TreeDTO;
import com.example.Citronix.dto.tree.TreeUpdateDTO;
import com.example.Citronix.model.Tree;
import com.example.Citronix.service.impl.TreeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @PostMapping
    public ResponseEntity<String> createTree(@Valid @RequestBody TreeCreateDTO treeCreateDTO) {
        try {
            treeService.createTree(treeCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tree created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/field/{fieldId}/productivity")
    public ResponseEntity<Double> getTotalProductivity(@PathVariable Long fieldId) {
        double productivity = treeService.calculateTotalProductivity(fieldId);
        return ResponseEntity.ok(productivity);
    }

    @GetMapping
    public ResponseEntity<List<TreeDTO>> getAllTreesWithAge() {
        List<TreeDTO> trees = treeService.getAllTreesWithAge();
        return ResponseEntity.ok(trees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTree(
            @PathVariable Long id,
            @RequestBody TreeUpdateDTO treeUpdateDTO) {
        Tree updatedTree = treeService.updateTree(id, treeUpdateDTO);
        return ResponseEntity.ok("tree updated");
    }
}
