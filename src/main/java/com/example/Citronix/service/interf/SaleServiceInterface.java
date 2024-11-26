package com.example.Citronix.service.interf;

import com.example.Citronix.dto.sale.SaleCreateDTO;
import com.example.Citronix.dto.sale.SaleDTO;
import com.example.Citronix.dto.sale.SaleUpdateDTO;
import com.example.Citronix.model.Sale;

import java.util.List;

public interface SaleServiceInterface {


    List<SaleDTO> getAllSales();


    SaleDTO getSaleById(Long id);


    Sale createSale(SaleCreateDTO saleCreateDTO);


    Sale updateSale(Long id, SaleUpdateDTO saleUpdateDTO);


    void deleteSale(Long id);
}
