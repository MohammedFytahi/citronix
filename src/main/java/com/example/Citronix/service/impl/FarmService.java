package com.example.Citronix.service.impl;

import com.example.Citronix.dto.farm.FarmSearchCriteria;
import com.example.Citronix.dto.field.FieldResponseDTO;
import com.example.Citronix.dto.farm.FarmCreateDTO;

import com.example.Citronix.dto.farm.FarmDTO;
import com.example.Citronix.dto.farm.FarmUpdateDTO;
import com.example.Citronix.enums.Season;
import com.example.Citronix.mapper.FarmMapper;
import com.example.Citronix.model.Farm;
import com.example.Citronix.repository.FarmRepository;
import com.example.Citronix.repository.FarmRepositoryCustom;
import com.example.Citronix.service.interf.FarmeServiceInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
    public class FarmService implements FarmeServiceInterface {



    private final FarmRepository farmRepository;

    @Qualifier("farmRepositoryCustomImpl")
    private final FarmRepositoryCustom farmRepositoryCustom;

    @Override
    public void addFarm(FarmCreateDTO farmCreateDTO) {
        try {

            Farm farm = FarmMapper.INSTANCE.toEntity(farmCreateDTO);


            farm.setCreationDate(farmCreateDTO.getCreationDate() != null ? farmCreateDTO.getCreationDate() : LocalDate.now());


            if (farm.getArea() < 1000) {
                throw new IllegalArgumentException("La surface de la ferme doit être d'au moins 1000 m².");
            }


            farmRepository.save(farm);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la ferme: " + e.getMessage(), e);
        }
    }


    @Override
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


        if (existingFarm.getArea() < 1000) {
            throw new IllegalArgumentException("Farm area must be at least 1000 m².");
        }


        double totalFieldArea = existingFarm.calculateTotalFieldArea();
        if (totalFieldArea > (existingFarm.getArea() * 0.5)) {
            throw new IllegalArgumentException("The total area of fields cannot exceed 50% of the farm's total area.");
        }

        farmRepository.save(existingFarm);
    }
     @Override
    public FarmDTO getFarm(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        return FarmMapper.INSTANCE.farmToFarmDTO(farm);
    }

    @Override
     public void deleteFarm(Long id) {

        if (!farmRepository.existsById(id)) {
            throw new RuntimeException("Farm with ID " + id + " does not exist");
        }

        farmRepository.deleteById(id);
    }




    @Override
    public List<FarmDTO> getAllFarms() {
        return farmRepository.findAll()
                .stream()
                .map(farm -> FarmDTO.builder()
                        .id(farm.getId())
                        .name(farm.getName())
                        .location(farm.getLocation())
                        .area(farm.getArea())
                        .creationDate(farm.getCreationDate())
                        .fields(farm.getFields().stream()
                                .map(field -> FieldResponseDTO.builder()
                                        .id(field.getId())
                                        .name(field.getName())
                                        .area(field.getArea())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<FarmDTO> searchFarms(FarmSearchCriteria criteria) {
        List<Farm> farms = farmRepositoryCustom.searchFarms(criteria);
        return farms.stream()
                .map(FarmMapper.INSTANCE::farmToFarmDTO)
                .collect(Collectors.toList());
    }




}
