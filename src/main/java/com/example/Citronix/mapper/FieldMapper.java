package com.example.Citronix.mapper;

import com.example.Citronix.dto.field.FieldCreateDTO;
import com.example.Citronix.dto.field.FieldDTO;
import com.example.Citronix.dto.field.FieldUpdateDTO;
import com.example.Citronix.model.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);




    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", ignore = true)
    Field toEntity(FieldCreateDTO fieldCreateDTO);

    Field toEntity(FieldUpdateDTO fieldUpdateDTO);
}
