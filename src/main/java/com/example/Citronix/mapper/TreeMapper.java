package com.example.Citronix.mapper;

import com.example.Citronix.dto.tree.TreeCreateDTO;
import com.example.Citronix.dto.tree.TreeDTO;
import com.example.Citronix.dto.tree.TreeUpdateDTO;
import com.example.Citronix.model.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    TreeMapper INSTANCE = Mappers.getMapper(TreeMapper.class);

    Tree toEntity(TreeCreateDTO treeCreateDTO);

    @Mapping(target = "age", expression = "java(tree.calculateAge())")
    @Mapping(target = "fieldName", source = "field.name")
    TreeDTO toResponseDTO(Tree tree);

    void updateEntityFromDTO(TreeUpdateDTO treeUpdateDTO, @MappingTarget Tree tree);

}
