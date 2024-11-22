package com.example.Citronix.controller;

import com.example.Citronix.dto.SaleCreateDTO;
import com.example.Citronix.dto.SaleUpdateDTO;
import com.example.Citronix.model.Sale;
import com.example.Citronix.service.impl.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<Sale> createSale(@Valid @RequestBody SaleCreateDTO saleCreateDTO) {
        Sale sale = saleService.createSale(saleCreateDTO);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(
            @PathVariable Long id,
            @Valid @RequestBody SaleUpdateDTO saleUpdateDTO) {

        // Appeler le service pour mettre à jour la vente
        Sale updatedSale = saleService.updateSale(id, saleUpdateDTO);

        return ResponseEntity.ok(updatedSale);
    }
}
