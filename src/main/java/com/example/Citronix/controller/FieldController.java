package com.example.Citronix.controller;

import com.example.Citronix.dto.field.FieldCreateDTO;
import com.example.Citronix.dto.field.FieldDTO;
import com.example.Citronix.dto.field.FieldUpdateDTO;
 import com.example.Citronix.service.impl.FieldService;
import com.example.Citronix.service.interf.FieldServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private FieldServiceInterface fieldService;

    @PostMapping
    public ResponseEntity<String> createField(@Valid @RequestBody FieldCreateDTO fieldCreateDTO) {
        try {
            fieldService.createField(fieldCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Field created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateField(@Valid @RequestBody FieldUpdateDTO fieldUpdateDTO) {
        FieldDTO updatedField = fieldService.updateField(fieldUpdateDTO);
        return ResponseEntity.ok("Field updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.ok("Field deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields(){
        List<FieldDTO> fields = fieldService.getAllFields();
        return ResponseEntity.ok(fields);
    }
}
