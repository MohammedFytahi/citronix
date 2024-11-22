package com.example.Citronix.service.impl;

import com.example.Citronix.dto.tree.TreeCreateDTO;
import com.example.Citronix.dto.tree.TreeDTO;
import com.example.Citronix.dto.tree.TreeUpdateDTO;
import com.example.Citronix.mapper.TreeMapper;
import com.example.Citronix.model.Field;
import com.example.Citronix.model.Tree;
import com.example.Citronix.repository.FieldRepository;
import com.example.Citronix.repository.TreeRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private TreeMapper treeMapper;

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
    @Transactional
    public Tree updateTree(Long id, TreeUpdateDTO treeUpdateDTO) {
        // Rechercher l'arbre existant
        Tree existingTree = treeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tree not found with ID: " + id));

        // Mise à jour de la date de plantation si fournie
        if (treeUpdateDTO.getPlantingDate() != null) {
            existingTree.setPlantingDate(treeUpdateDTO.getPlantingDate());
        }

        // Mise à jour du champ si un nouveau fieldId est fourni
        if (treeUpdateDTO.getFieldId() != null) {
            Field newField = fieldRepository.findById(treeUpdateDTO.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + treeUpdateDTO.getFieldId()));
            existingTree.setField(newField);
        }

        // Sauvegarder les modifications
        return treeRepository.save(existingTree);
    }
}
