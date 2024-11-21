package com.example.Citronix.mapper;

import com.example.Citronix.dto.HarvestCreateDTO;
import com.example.Citronix.model.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestMapper INSTANCE = Mappers.getMapper(HarvestMapper.class);


    Harvest toEntity(HarvestCreateDTO harvestCreateDTO);


}
