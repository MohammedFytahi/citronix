package com.example.Citronix.service.interf;

import com.example.Citronix.dto.tree.TreeCreateDTO;
import com.example.Citronix.dto.tree.TreeDTO;
import com.example.Citronix.dto.tree.TreeUpdateDTO;
import com.example.Citronix.model.Tree;

import java.util.List;

public interface TreeServiceInterface {

    /**
     * Crée un nouvel arbre.
     *
     * @param treeCreateDTO Les informations pour créer un arbre.
     */
    void createTree(TreeCreateDTO treeCreateDTO);

    /**
     * Récupère la liste des arbres associés à un champ.
     *
     * @param fieldId L'ID du champ.
     * @return Une liste d'arbres.
     */
    List<Tree> getTreesByField(Long fieldId);

    /**
     * Calcule la productivité totale annuelle pour un champ donné.
     *
     * @param fieldId L'ID du champ.
     * @return La productivité totale annuelle.
     */
    double calculateTotalProductivity(Long fieldId);

    /**
     * Récupère la liste de tous les arbres avec leurs âges.
     *
     * @return Une liste de DTO des arbres avec leur âge.
     */
    List<TreeDTO> getAllTreesWithAge();

    /**
     * Met à jour un arbre existant.
     *
     * @param id L'ID de l'arbre à mettre à jour.
     * @param treeUpdateDTO Les informations de mise à jour.
     * @return L'arbre mis à jour.
     */
    Tree updateTree(Long id, TreeUpdateDTO treeUpdateDTO);
}
