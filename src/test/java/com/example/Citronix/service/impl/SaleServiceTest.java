package com.example.Citronix.service.impl;

import com.example.Citronix.dto.sale.SaleCreateDTO;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.model.Sale;
import com.example.Citronix.repository.HarvestRepository;
import com.example.Citronix.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleServiceTest {

    @InjectMocks
    private SaleService saleService;

    @Mock
    private HarvestRepository harvestRepository;

    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSale_Success() {
         SaleCreateDTO saleCreateDTO = new SaleCreateDTO();
        saleCreateDTO.setSaleDate(LocalDate.of(2024, 11, 22));
        saleCreateDTO.setUnitPrice(10.0);
        saleCreateDTO.setClientName("Client Test");
        saleCreateDTO.setHarvestId(1L);

         Harvest harvest = new Harvest();
        harvest.setId(1L);

        HarvestDetail detail1 = new HarvestDetail();
        detail1.setQuantity(50.0);

        HarvestDetail detail2 = new HarvestDetail();
        detail2.setQuantity(100.0);

        harvest.setHarvestDetails(Arrays.asList(detail1, detail2));

        when(harvestRepository.findById(1L)).thenReturn(Optional.of(harvest));

         Sale savedSale = new Sale();
        savedSale.setId(1L);
        savedSale.setSaleDate(saleCreateDTO.getSaleDate());
        savedSale.setUnitPrice(saleCreateDTO.getUnitPrice());
        savedSale.setClientName(saleCreateDTO.getClientName());
        savedSale.setHarvest(harvest);
        savedSale.setQuantity(150.0);

        when(saleRepository.save(any(Sale.class))).thenReturn(savedSale);

         Sale result = saleService.createSale(saleCreateDTO);

         assertNotNull(result);
        assertEquals(1L, result.getHarvest().getId());
        assertEquals(150.0, result.getQuantity());
        assertEquals("Client Test", result.getClientName());
        assertEquals(10.0, result.getUnitPrice());
        assertEquals(LocalDate.of(2024, 11, 22), result.getSaleDate());

         verify(harvestRepository, times(1)).findById(1L);
        verify(saleRepository, times(1)).save(any(Sale.class));
    }

    @Test
    void testCreateSale_HarvestNotFound() {
         SaleCreateDTO saleCreateDTO = new SaleCreateDTO();
        saleCreateDTO.setHarvestId(99L);

        when(harvestRepository.findById(99L)).thenReturn(Optional.empty());

         Exception exception = assertThrows(IllegalArgumentException.class, () ->
                saleService.createSale(saleCreateDTO)
        );

        assertEquals("Récolte non trouvée pour l'ID: 99", exception.getMessage());

         verify(saleRepository, never()).save(any(Sale.class));
    }
}
