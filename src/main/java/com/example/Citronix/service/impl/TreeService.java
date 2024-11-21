package com.example.Citronix.service.impl;

import com.example.Citronix.dto.TreeCreateDTO;
import com.example.Citronix.dto.TreeDTO;
import com.example.Citronix.mapper.TreeMapper;
import com.example.Citronix.model.Field;
import com.example.Citronix.model.Tree;
import com.example.Citronix.repository.FieldRepository;
import com.example.Citronix.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public void createTree(TreeCreateDTO treeCreateDTO) {
        Field field = fieldRepository.findById(treeCreateDTO.getFieldId())
                .orElseThrow(() -> new IllegalArgumentException("Field not found for ID: " + treeCreateDTO.getFieldId()));

        Tree tree = TreeMapper.INSTANCE.toEntity(treeCreateDTO);
        tree.setField(field);
        treeRepository.save(tree);
    }

    public List<Tree> getTreesByField(Long fieldId) {
        return treeRepository.findByFieldId(fieldId);
    }

    public double calculateTotalProductivity(Long fieldId) {
        List<Tree> trees = getTreesByField(fieldId);
        return trees.stream().mapToDouble(Tree::calculateAnnualProductivity).sum();
    }

    public List<TreeDTO> getAllTreesWithAge() {
        List<Tree> trees = treeRepository.findAll();
        return trees.stream()
                .map(TreeMapper.INSTANCE::toResponseDTO)
                .collect(Collectors.toList());
    }
}
