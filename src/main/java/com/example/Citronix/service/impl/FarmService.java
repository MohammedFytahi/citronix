package com.example.Citronix.service.impl;

import com.example.Citronix.dto.FarmCreateDTO;

import com.example.Citronix.dto.FarmDTO;
import com.example.Citronix.dto.FarmUpdateDTO;
import com.example.Citronix.mapper.FarmMapper;
import com.example.Citronix.model.Farm;
import com.example.Citronix.repository.FarmRepository;
import com.example.Citronix.service.interf.FarmeServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FarmService implements FarmeServiceInterface {

    @Autowired
    private FarmRepository farmRepository;



    @Override
    public void addFarm(FarmCreateDTO farmCreateDTO) {
        try {
            // Conversion de FarmCreateDTO en entité Farm
            Farm farm = FarmMapper.INSTANCE.toEntity(farmCreateDTO);

            // Assurer la création de la ferme avec la date actuelle si non fournie
            farm.setCreationDate(farmCreateDTO.getCreationDate() != null ? farmCreateDTO.getCreationDate() : LocalDate.now());

            // Validation de l'area
            if (farm.getArea() < 1000) {
                throw new IllegalArgumentException("La surface de la ferme doit être d'au moins 1000 m².");
            }

            // Sauvegarde dans la base de données
            farmRepository.save(farm);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la ferme: " + e.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public void updateFarm(Long id, FarmUpdateDTO farmUpdateDTO) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        // Mettre à jour les valeurs de la ferme si elles sont présentes dans le DTO
        if (farmUpdateDTO.getName() != null) {
            existingFarm.setName(farmUpdateDTO.getName());
        }
        if (farmUpdateDTO.getLocation() != null) {
            existingFarm.setLocation(farmUpdateDTO.getLocation());
        }
        if (farmUpdateDTO.getArea() != null) {
            existingFarm.setArea(farmUpdateDTO.getArea());
        }

        // Validation de la superficie minimale
        if (existingFarm.getArea() < 1000) {
            throw new IllegalArgumentException("Farm area must be at least 1000 m².");
        }

        // Vérifier que la superficie totale des champs ne dépasse pas 50% de la superficie de la ferme
        double totalFieldArea = existingFarm.calculateTotalFieldArea();
        if (totalFieldArea > (existingFarm.getArea() * 0.5)) {
            throw new IllegalArgumentException("The total area of fields cannot exceed 50% of the farm's total area.");
        }

        farmRepository.save(existingFarm);
    }

    public FarmDTO getFarm(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        return FarmMapper.INSTANCE.farmToFarmDTO(farm);
    }

    @Transactional
    public void deleteFarm(Long id) {

        if (!farmRepository.existsById(id)) {
            throw new RuntimeException("Farm with ID " + id + " does not exist");
        }

        farmRepository.deleteById(id);
    }


    public List<FarmDTO> getAllFarms() {
        return farmRepository.findAll()
                .stream()
                .map(farm -> FarmDTO.builder()
                        .id(farm.getId())
                        .name(farm.getName())
                        .location(farm.getLocation())
                        .area(farm.getArea())
                        .creationDate(farm.getCreationDate())
                        .build())
                .collect(Collectors.toList());
    }

}
