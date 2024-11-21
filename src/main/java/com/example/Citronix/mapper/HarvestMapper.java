package com.example.Citronix.mapper;

import com.example.Citronix.dto.HarvestCreateDTO;
import com.example.Citronix.dto.HarvestDetailDTO;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.HarvestDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestMapper INSTANCE = Mappers.getMapper(HarvestMapper.class);

    @Mapping(target = "harvestDetails", ignore = true) // Les détails seront ajoutés manuellement
    Harvest toEntity(HarvestCreateDTO harvestCreateDTO);

    
    HarvestCreateDTO toDTO(Harvest harvest);


    HarvestDetailDTO toDetailDTO(HarvestDetail detail);

    List<HarvestDetailDTO> toDetailDTOs(List<HarvestDetail> details);
}
