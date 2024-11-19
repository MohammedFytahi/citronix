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


@Service
public class FarmService implements FarmeServiceInterface {

    @Autowired
    private FarmRepository farmRepository;

    @Override

    public void addFarm(FarmCreateDTO farmCreateDTO) {
        Farm farm = FarmMapper.INSTANCE.toEntity(farmCreateDTO);
        // Ajouter la date de création si elle est absente
        farm.setCreationDate(farmCreateDTO.getCreationDate() != null ? farmCreateDTO.getCreationDate() : LocalDate.now());

        // Enregistrer l'entité
        farmRepository.save(farm);


    }

    @Override
    @Transactional
    public void updateFarm(Long id, FarmUpdateDTO farmUpdateDTO) {
        // Récupérer la ferme existante
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        // Mapper les données du DTO vers l'entité existante
        if (farmUpdateDTO.getName() != null) {
            existingFarm.setName(farmUpdateDTO.getName());
        }
        if (farmUpdateDTO.getLocation() != null) {
            existingFarm.setLocation(farmUpdateDTO.getLocation());
        }
        if (farmUpdateDTO.getArea() != null) {
            existingFarm.setArea(farmUpdateDTO.getArea());
        }


        // Sauvegarder les modifications
        farmRepository.save(existingFarm);
    }

    public FarmDTO getFarm(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        return FarmMapper.INSTANCE.farmToFarmDTO(farm);
    }


}
