package com.example.Citronix.controller;

import com.example.Citronix.dto.sale.SaleCreateDTO;
import com.example.Citronix.dto.sale.SaleDTO;
import com.example.Citronix.dto.sale.SaleUpdateDTO;
import com.example.Citronix.model.Sale;
import com.example.Citronix.service.impl.SaleService;
import com.example.Citronix.service.interf.SaleServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleServiceInterface saleService;

    @PostMapping
    public ResponseEntity<Sale> createSale(@Valid @RequestBody SaleCreateDTO saleCreateDTO) {
        Sale sale = saleService.createSale(saleCreateDTO);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(
            @PathVariable Long id,
            @Valid @RequestBody SaleUpdateDTO saleUpdateDTO) {

         Sale updatedSale = saleService.updateSale(id, saleUpdateDTO);

        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {
        try {
             saleService.deleteSale(id);
            return ResponseEntity.ok("La vente avec l'ID " + id + " a été supprimée avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) {
        SaleDTO sale = saleService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }
}
