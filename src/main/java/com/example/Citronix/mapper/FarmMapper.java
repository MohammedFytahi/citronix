package com.example.Citronix.mapper;

import com.example.Citronix.dto.FarmCreateDTO;
import com.example.Citronix.dto.FarmDTO;

import com.example.Citronix.dto.FarmUpdateDTO;
import com.example.Citronix.model.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    FarmDTO farmToFarmDTO(Farm farm);

    Farm farmDTOToFarm(FarmDTO farmDto);
    @Mapping(target = "creationDate", ignore = true) // Ignorer la date de création
    Farm toEntity(FarmCreateDTO farmCreateDTO);

    Farm toEntity(FarmUpdateDTO farmUpdateDTO);
}
