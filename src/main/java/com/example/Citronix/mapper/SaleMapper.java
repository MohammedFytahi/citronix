package com.example.Citronix.mapper;

import com.example.Citronix.dto.SaleDTO;
import com.example.Citronix.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    @Mapping(target = "harvestId", source = "harvest.id")
    @Mapping(target = "harvestSeason", source = "harvest.season")
    @Mapping(target = "harvestDate", source = "harvest.harvestDate")
    @Mapping(target = "revenue", expression = "java(sale.getUnitPrice() * sale.getQuantity())")
    SaleDTO toDTO(Sale sale);
}
