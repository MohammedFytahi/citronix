package com.example.Citronix.mapper;

import com.example.Citronix.dto.HarvestDetailCreateDTO;
import com.example.Citronix.model.HarvestDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {

    HarvestDetailMapper INSTANCE = Mappers.getMapper(HarvestDetailMapper.class);



    @Mapping(target = "harvest.id", source = "harvestId")
    @Mapping(target = "tree.id", source = "treeId")
    HarvestDetail toEntity(HarvestDetailCreateDTO harvestDetailCreateDTO);
}
