package com.example.Citronix.service.impl;

import com.example.Citronix.dto.HarvestCreateDTO;
import com.example.Citronix.mapper.HarvestMapper;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.model.Tree;
import com.example.Citronix.repository.HarvestRepository;
import com.example.Citronix.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private TreeRepository treeRepository;

    @Autowired
    private HarvestMapper harvestMapper;

    @Transactional
    public Harvest createHarvest(HarvestCreateDTO harvestCreateDTO, Long fieldId) {

        if (harvestRepository.findBySeasonAndHarvestDetails_Tree_Field_Id(harvestCreateDTO.getSeason(), fieldId).isPresent()) {
            throw new IllegalArgumentException("Une récolte existe déjà pour cette saison dans ce champ.");
        }


        Harvest harvest = harvestMapper.toEntity(harvestCreateDTO);


        if (harvest.getHarvestDetails() == null) {
            harvest.setHarvestDetails(new ArrayList<>());
        }

        // Récupérer les arbres associés au champ
        List<Tree> trees = treeRepository.findByFieldId(fieldId);
        for (Tree tree : trees) {
            HarvestDetail detail = new HarvestDetail();
            detail.setHarvest(harvest); // Associe la récolte
            detail.setTree(tree); // Associe l'arbre
            detail.setQuantity(tree.calculateAnnualProductivity()); // Calcul de la quantité récoltée par arbre
            harvest.getHarvestDetails().add(detail); // Ajouter les détails à la récolte
        }

        // Sauvegarder la récolte et ses détails
        return harvestRepository.save(harvest); // Sauvegarde la récolte, ainsi que ses détails
    }
}
