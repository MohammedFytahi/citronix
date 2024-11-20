package com.example.Citronix.mapper;

import com.example.Citronix.dto.FieldCreateDTO;
import com.example.Citronix.dto.FieldDTO;
import com.example.Citronix.dto.FieldUpdateDTO;
import com.example.Citronix.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);


    @Mapping(target = "farmId", source = "farm.id")
    @Mapping(target = "farmName", source = "farm.name")
    FieldDTO fieldToFieldDTO(Field field);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", ignore = true)
    Field toEntity(FieldCreateDTO fieldCreateDTO);

    Field toEntity(FieldUpdateDTO fieldUpdateDTO);
}
