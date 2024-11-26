package com.example.Citronix.service.interf;

import com.example.Citronix.dto.tree.TreeCreateDTO;
import com.example.Citronix.dto.tree.TreeDTO;
import com.example.Citronix.dto.tree.TreeUpdateDTO;
import com.example.Citronix.model.Tree;

import java.util.List;

public interface TreeServiceInterface {


    void createTree(TreeCreateDTO treeCreateDTO);


    List<Tree> getTreesByField(Long fieldId);


    double calculateTotalProductivity(Long fieldId);


    List<TreeDTO> getAllTreesWithAge();

    Tree updateTree(Long id, TreeUpdateDTO treeUpdateDTO);
}
