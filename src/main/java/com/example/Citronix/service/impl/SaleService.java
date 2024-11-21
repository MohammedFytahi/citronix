package com.example.Citronix.service.impl;

import com.example.Citronix.dto.SaleCreateDTO;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.Sale;
import com.example.Citronix.repository.HarvestRepository;
import com.example.Citronix.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Transactional
    public Sale createSale(SaleCreateDTO saleCreateDTO) {
        // Vérifier si la récolte existe
        Harvest harvest = harvestRepository.findById(saleCreateDTO.getHarvestId())
                .orElseThrow(() -> new IllegalArgumentException("Récolte non trouvée pour l'ID: " + saleCreateDTO.getHarvestId()));

        // Créer une entité `Sale`
        Sale sale = new Sale();
        sale.setSaleDate(saleCreateDTO.getSaleDate());
        sale.setUnitPrice(saleCreateDTO.getUnitPrice());
        sale.setQuantity(saleCreateDTO.getQuantity());
        sale.setClientName(saleCreateDTO.getClientName());
        sale.setHarvest(harvest);

        // Enregistrer la vente
        return saleRepository.save(sale);
    }
}
