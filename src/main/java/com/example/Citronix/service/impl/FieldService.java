package com.example.Citronix.service.impl;

import com.example.Citronix.dto.FieldCreateDTO;
import com.example.Citronix.dto.FieldUpdateDTO;
import com.example.Citronix.mapper.FieldMapper;
import com.example.Citronix.model.Farm;
import com.example.Citronix.model.Field;
import com.example.Citronix.repository.FarmRepository;
import com.example.Citronix.repository.FieldRepository;
import com.example.Citronix.service.interf.FieldServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldService implements FieldServiceInterface {

    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    FarmRepository farmRepository;

    @Autowired
    FieldMapper fieldMapper;

    @Override
    @Transactional
    public void createField(FieldCreateDTO fieldCreateDTO){

        Farm farm = farmRepository.findById(fieldCreateDTO.getFarmId())
                .orElseThrow(() -> new IllegalArgumentException("Farm not found for ID: " + fieldCreateDTO.getFarmId()));


        double totalFieldArea = farm.calculateTotalFieldArea();


        if (fieldCreateDTO.getArea() < 1000) {
            throw new IllegalArgumentException("The minimum area for a field is 1000 m² (0.1 hectare).");
        }


        if (totalFieldArea + fieldCreateDTO.getArea() > (farm.getArea() / 2)) {
            throw new IllegalArgumentException("The field area cannot exceed 50% of the total farm area.");
        }


        long numberOfFields = fieldRepository.countByFarmId(farm.getId());
        if (numberOfFields >= 10) {
            throw new IllegalArgumentException("A farm cannot have more than 10 fields.");
        }


        Field field = fieldMapper.toEntity(fieldCreateDTO);
        field.setFarm(farm);


        fieldRepository.save(field);
    }

    @Transactional
    @Override
    public Field updateField(FieldUpdateDTO fieldUpdateDTO) {
        Field field = fieldMapper.toEntity(fieldUpdateDTO);

        Farm farm = farmRepository.findById(fieldUpdateDTO.getFarmId())
                .orElseThrow(() -> new IllegalArgumentException("Farm not found for ID: " + fieldUpdateDTO.getFarmId()));
        field.setFarm(farm);

        return fieldRepository.save(field);
    }
    }



