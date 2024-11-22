package com.example.Citronix.service.impl;

import com.example.Citronix.dto.sale.SaleCreateDTO;
import com.example.Citronix.dto.sale.SaleDTO;
import com.example.Citronix.dto.sale.SaleUpdateDTO;
import com.example.Citronix.mapper.SaleMapper;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.model.Sale;
import com.example.Citronix.repository.HarvestRepository;
import com.example.Citronix.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private SaleMapper saleMapper;

    public List<SaleDTO> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(saleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SaleDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La vente avec l'ID " + id + " n'existe pas."));
        return saleMapper.toDTO(sale);
    }

    @Transactional
    public Sale createSale(SaleCreateDTO saleCreateDTO) {
         Harvest harvest = harvestRepository.findById(saleCreateDTO.getHarvestId())
                .orElseThrow(() -> new IllegalArgumentException("Récolte non trouvée pour l'ID: " + saleCreateDTO.getHarvestId()));

         List<HarvestDetail> harvestDetails = harvest.getHarvestDetails();
        double totalQuantity = harvestDetails.stream()
                .mapToDouble(HarvestDetail::getQuantity)
                .sum();

         Sale sale = new Sale();
        sale.setSaleDate(saleCreateDTO.getSaleDate());
        sale.setUnitPrice(saleCreateDTO.getUnitPrice());
        sale.setClientName(saleCreateDTO.getClientName());
        sale.setHarvest(harvest);
        sale.setQuantity(totalQuantity); // Quantité calculée

         return saleRepository.save(sale);
    }

    @Transactional
    public Sale updateSale(Long id, SaleUpdateDTO saleUpdateDTO) {
         Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vente non trouvée pour l'ID : " + id));

         if (saleUpdateDTO.getSaleDate() != null) {
            existingSale.setSaleDate(saleUpdateDTO.getSaleDate());
        }

        if (saleUpdateDTO.getUnitPrice() != null) {
            existingSale.setUnitPrice(saleUpdateDTO.getUnitPrice());
        }

        if (saleUpdateDTO.getClientName() != null) {
            existingSale.setClientName(saleUpdateDTO.getClientName());
        }

         return saleRepository.save(existingSale);
    }

    @Transactional
    public void deleteSale(Long id) {
         if (!saleRepository.existsById(id)) {
            throw new IllegalArgumentException("La vente avec l'ID " + id + " n'existe pas.");
        }

         saleRepository.deleteById(id);
    }


}
