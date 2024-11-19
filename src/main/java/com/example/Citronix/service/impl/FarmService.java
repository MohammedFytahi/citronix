package com.example.Citronix.service.impl;

import com.example.Citronix.dto.FarmCreateDTO;

import com.example.Citronix.dto.FarmUpdateDTO;
import com.example.Citronix.mapper.FarmMapper;
import com.example.Citronix.model.Farm;
import com.example.Citronix.repository.FarmRepository;
import com.example.Citronix.service.interf.FarmeServiceInterface;
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
    public void updateFarm(Long farmId, FarmUpdateDTO farmUpdateDTO){

    }


}
