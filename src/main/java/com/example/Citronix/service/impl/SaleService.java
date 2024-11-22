package com.example.Citronix.service.impl;

import com.example.Citronix.dto.SaleCreateDTO;
import com.example.Citronix.dto.SaleUpdateDTO;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.model.Sale;
import com.example.Citronix.repository.HarvestRepository;
import com.example.Citronix.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        // Calculer la quantité totale à partir des détails de la récolte
        List<HarvestDetail> harvestDetails = harvest.getHarvestDetails();
        double totalQuantity = harvestDetails.stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();

        // Créer la vente
        Sale sale = new Sale();
        sale.setSaleDate(saleCreateDTO.getSaleDate());
        sale.setUnitPrice(saleCreateDTO.getUnitPrice());
        sale.setClientName(saleCreateDTO.getClientName());
        sale.setHarvest(harvest);
        sale.setQuantity(totalQuantity); // Quantité calculée

        // Sauvegarder la vente
        return saleRepository.save(sale);
    }

    @Transactional
    public Sale updateSale(Long id, SaleUpdateDTO saleUpdateDTO) {
        // Récupérer la vente existante par son ID
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vente non trouvée pour l'ID : " + id));

        // Mettre à jour uniquement les champs modifiables
        if (saleUpdateDTO.getSaleDate() != null) {
            existingSale.setSaleDate(saleUpdateDTO.getSaleDate());
        }

        if (saleUpdateDTO.getUnitPrice() != null) {
            existingSale.setUnitPrice(saleUpdateDTO.getUnitPrice());
        }

        if (saleUpdateDTO.getClientName() != null) {
            existingSale.setClientName(saleUpdateDTO.getClientName());
        }

        // Sauvegarder les modifications
        return saleRepository.save(existingSale);
    }
}
