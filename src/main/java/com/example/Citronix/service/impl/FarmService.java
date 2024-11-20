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
        Farm farm = FarmMapper.INSTANCE.toEntity(farmCreateDTO);


        farm.setCreationDate(farmCreateDTO.getCreationDate() != null ? farmCreateDTO.getCreationDate() : LocalDate.now());


        farmRepository.save(farm);


    }

    @Override
    @Transactional
    public void updateFarm(Long id, FarmUpdateDTO farmUpdateDTO) {

        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));


        if (farmUpdateDTO.getName() != null) {
            existingFarm.setName(farmUpdateDTO.getName());
        }
        if (farmUpdateDTO.getLocation() != null) {
            existingFarm.setLocation(farmUpdateDTO.getLocation());
        }
        if (farmUpdateDTO.getArea() != null) {
            existingFarm.setArea(farmUpdateDTO.getArea());
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
