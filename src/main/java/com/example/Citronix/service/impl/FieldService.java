package com.example.Citronix.service.impl;

import com.example.Citronix.dto.field.FieldCreateDTO;
import com.example.Citronix.dto.field.FieldDTO;
import com.example.Citronix.dto.field.FieldUpdateDTO;
import com.example.Citronix.dto.farm.FarmSummaryDTO;
import com.example.Citronix.mapper.FieldMapper;
import com.example.Citronix.model.Farm;
import com.example.Citronix.model.Field;
import com.example.Citronix.repository.FarmRepository;
import com.example.Citronix.repository.FieldRepository;
import com.example.Citronix.service.interf.FieldServiceInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FieldService implements FieldServiceInterface {


    private final FieldRepository fieldRepository;


    private final FarmRepository farmRepository;


    private final FieldMapper fieldMapper;

    @Override
    @Transactional
    public void createField(FieldCreateDTO fieldCreateDTO) {
        Farm farm = farmRepository.findById(fieldCreateDTO.getFarmId())
                .orElseThrow(() -> new IllegalArgumentException("Farm not found for ID: " + fieldCreateDTO.getFarmId()));

        double totalFieldArea = farm.calculateTotalFieldArea();

        // Vérification de la superficie minimale
        if (fieldCreateDTO.getArea() < 1000) {
            throw new IllegalArgumentException("The minimum area for a field is 1000 m² (0.1 hectare).");
        }

        // Vérification de la limite de 50% de la superficie totale de la ferme
        if (totalFieldArea + fieldCreateDTO.getArea() > (farm.getArea() / 2)) {
            throw new IllegalArgumentException("The field area cannot exceed 50% of the total farm area.");
        }

        // Vérification du nombre maximal de champs par ferme
        long numberOfFields = fieldRepository.countByFarmId(farm.getId());
        if (numberOfFields >= 10) {
            throw new IllegalArgumentException("A farm cannot have more than 10 fields.");
        }

        // Création du champ
        Field field = fieldMapper.toEntity(fieldCreateDTO);
        field.setFarm(farm);

        fieldRepository.save(field);
    }

    @Override
    @Transactional
    public FieldDTO updateField(FieldUpdateDTO fieldUpdateDTO) {
        // Récupérer le champ existant
        Field existingField = fieldRepository.findById(fieldUpdateDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Field not found for ID: " + fieldUpdateDTO.getId()));

        // Mise à jour des informations
        if (fieldUpdateDTO.getName() != null) {
            existingField.setName(fieldUpdateDTO.getName());
        }
        if (fieldUpdateDTO.getArea() != null) {
            existingField.setArea(fieldUpdateDTO.getArea());
        }

        if (fieldUpdateDTO.getFarmId() != null) {
            Farm farm = farmRepository.findById(fieldUpdateDTO.getFarmId())
                    .orElseThrow(() -> new IllegalArgumentException("Farm not found for ID: " + fieldUpdateDTO.getFarmId()));
            existingField.setFarm(farm);
        }

         Field updatedField = fieldRepository.save(existingField);
        return fieldMapper.toDTO(updatedField);
    }

    @Override
    @Transactional
    public void deleteField(Long id) {
        if (!fieldRepository.existsById(id)) {
            throw new RuntimeException("Field with ID " + id + " does not exist");
        }
        fieldRepository.deleteById(id);
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return fieldRepository.findAll()
                .stream()
                .map(field -> FieldDTO.builder()
                        .id(field.getId())
                        .name(field.getName())
                        .area(field.getArea())
                        .farm(FarmSummaryDTO.builder()
                                .id(field.getFarm().getId())
                                .name(field.getFarm().getName())
                                .location(field.getFarm().getLocation())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }
}
