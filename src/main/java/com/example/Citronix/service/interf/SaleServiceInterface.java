package com.example.Citronix.service.interf;

import com.example.Citronix.dto.sale.SaleCreateDTO;
import com.example.Citronix.dto.sale.SaleDTO;
import com.example.Citronix.dto.sale.SaleUpdateDTO;
import com.example.Citronix.model.Sale;

import java.util.List;

public interface SaleServiceInterface {

    /**
     * Récupère toutes les ventes.
     *
     * @return Une liste de DTO des ventes.
     */
    List<SaleDTO> getAllSales();

    /**
     * Récupère une vente par son ID.
     *
     * @param id L'ID de la vente.
     * @return Le DTO de la vente.
     */
    SaleDTO getSaleById(Long id);

    /**
     * Crée une nouvelle vente.
     *
     * @param saleCreateDTO Les informations pour créer la vente.
     * @return La vente créée.
     */
    Sale createSale(SaleCreateDTO saleCreateDTO);

    /**
     * Met à jour une vente existante.
     *
     * @param id L'ID de la vente à mettre à jour.
     * @param saleUpdateDTO Les informations de mise à jour.
     * @return La vente mise à jour.
     */
    Sale updateSale(Long id, SaleUpdateDTO saleUpdateDTO);

    /**
     * Supprime une vente par son ID.
     *
     * @param id L'ID de la vente à supprimer.
     */
    void deleteSale(Long id);
}
