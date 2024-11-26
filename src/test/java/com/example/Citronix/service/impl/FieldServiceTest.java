package com.example.Citronix.service.impl;

import com.example.Citronix.dto.field.FieldCreateDTO;
import com.example.Citronix.mapper.FieldMapper;
import com.example.Citronix.model.Farm;
import com.example.Citronix.model.Field;
import com.example.Citronix.repository.FarmRepository;
import com.example.Citronix.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FieldServiceTest {

    @InjectMocks
    private FieldService fieldService;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FieldMapper fieldMapper;
    @Mock
    private Farm mockFarm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateField_Success() {
         FieldCreateDTO fieldCreateDTO = new FieldCreateDTO();
        fieldCreateDTO.setFarmId(1L);
        fieldCreateDTO.setArea(1000.0);

        Farm mockFarm = mock(Farm.class);
        when(mockFarm.getId()).thenReturn(1L);
        when(mockFarm.getArea()).thenReturn(5000.0);
        when(mockFarm.calculateTotalFieldArea()).thenReturn(1000.0);

        when(farmRepository.findById(1L)).thenReturn(Optional.of(mockFarm));
        when(fieldRepository.countByFarmId(1L)).thenReturn(5L);

        Field field = new Field();
        when(fieldMapper.toEntity(fieldCreateDTO)).thenReturn(field);

         fieldService.createField(fieldCreateDTO);

         verify(farmRepository, times(1)).findById(1L);
        verify(fieldRepository, times(1)).save(any(Field.class));
    }



    @Test
    void testCreateField_FarmNotFound() {
         FieldCreateDTO fieldCreateDTO = new FieldCreateDTO();
        fieldCreateDTO.setFarmId(99L);

        when(farmRepository.findById(99L)).thenReturn(Optional.empty());

         Exception exception = assertThrows(IllegalArgumentException.class, () ->
                fieldService.createField(fieldCreateDTO)
        );
        assertEquals("Farm not found for ID: 99", exception.getMessage());
        verify(fieldRepository, never()).save(any(Field.class));
    }

    @Test
    void testCreateField_AreaBelowMinimum() {
         FieldCreateDTO fieldCreateDTO = new FieldCreateDTO();
        fieldCreateDTO.setFarmId(1L);
        fieldCreateDTO.setArea(500.0);

        Farm farm = new Farm();
        farm.setId(1L);

        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

         Exception exception = assertThrows(IllegalArgumentException.class, () ->
                fieldService.createField(fieldCreateDTO)
        );
        assertEquals("The minimum area for a field is 1000 m² (0.1 hectare).", exception.getMessage());
        verify(fieldRepository, never()).save(any(Field.class));
    }

    @Test
    void testCreateField_TotalAreaExceedsLimit() {
         FieldCreateDTO fieldCreateDTO = new FieldCreateDTO();
        fieldCreateDTO.setFarmId(1L);
        fieldCreateDTO.setArea(3000.0);

        when(mockFarm.calculateTotalFieldArea()).thenReturn(2000.0);
        when(mockFarm.getArea()).thenReturn(5000.0);
        when(mockFarm.getId()).thenReturn(1L);
        when(farmRepository.findById(1L)).thenReturn(Optional.of(mockFarm));

         Exception exception = assertThrows(IllegalArgumentException.class, () ->
                fieldService.createField(fieldCreateDTO)
        );
        assertEquals("The field area cannot exceed 50% of the total farm area.", exception.getMessage());
        verify(fieldRepository, never()).save(any(Field.class));
    }

    @Test
    void testCreateField_MaxFieldsExceeded() {
         FieldCreateDTO fieldCreateDTO = new FieldCreateDTO();
        fieldCreateDTO.setFarmId(1L);
        fieldCreateDTO.setArea(2000.0);

        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(5000.0);
        farm.setFields(new ArrayList<>());

        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(fieldRepository.countByFarmId(1L)).thenReturn(10L);

         Exception exception = assertThrows(IllegalArgumentException.class, () ->
                fieldService.createField(fieldCreateDTO)
        );
        assertEquals("A farm cannot have more than 10 fields.", exception.getMessage());
        verify(fieldRepository, never()).save(any(Field.class));
    }

}
