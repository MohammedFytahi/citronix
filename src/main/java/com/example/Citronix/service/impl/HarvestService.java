package com.example.Citronix.service.impl;

import com.example.Citronix.dto.harvest.HarvestCreateDTO;
import com.example.Citronix.dto.harvest.HarvestDTO;
import com.example.Citronix.dto.harvest.HarvestUpdateDTO;
import com.example.Citronix.mapper.HarvestMapper;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.model.Tree;
import com.example.Citronix.repository.HarvestRepository;
import com.example.Citronix.repository.TreeRepository;
import com.example.Citronix.service.interf.HarvestServiceInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HarvestService implements HarvestServiceInterface {

     private final HarvestRepository harvestRepository;

     private final TreeRepository treeRepository;

     private final HarvestMapper harvestMapper;

    @Override
    @Transactional
    public Harvest createHarvest(HarvestCreateDTO harvestCreateDTO, Long fieldId) {
        // Vérifier si une récolte existe déjà pour cette saison et ce champ
        if (harvestRepository.findBySeasonAndHarvestDetails_Tree_Field_Id(harvestCreateDTO.getSeason(), fieldId).isPresent()) {
            throw new IllegalArgumentException("Une récolte existe déjà pour cette saison dans ce champ.");
        }

        // Convertir les données du DTO en entité
        Harvest harvest = harvestMapper.toEntity(harvestCreateDTO);

        if (harvest.getHarvestDetails() == null) {
            harvest.setHarvestDetails(new ArrayList<>());
        }

        // Récupérer tous les arbres associés au champ
        List<Tree> trees = treeRepository.findByFieldId(fieldId);
        for (Tree tree : trees) {
            HarvestDetail detail = new HarvestDetail();
            detail.setHarvest(harvest); // Associer la récolte
            detail.setTree(tree); // Associer l'arbre
            detail.setQuantity(tree.calculateAnnualProductivity());
            harvest.getHarvestDetails().add(detail);
        }

        return harvestRepository.save(harvest);
    }

    @Override
    @Transactional
    public Harvest updateHarvest(Long id, HarvestUpdateDTO harvestUpdateDTO) {
        // Vérifier si la récolte existe
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found with ID: " + id));

        // Mettre à jour les données de la récolte à partir du DTO
        harvestMapper.updateEntityFromDTO(harvestUpdateDTO, existingHarvest);

        return harvestRepository.save(existingHarvest);
    }

    @Override
    @Transactional
    public void deleteHarvest(Long id) {
        // Vérifier si la récolte existe
        if (!harvestRepository.existsById(id)) {
            throw new IllegalArgumentException("Harvest not found with ID: " + id);
        }

        // Supprimer la récolte
        harvestRepository.deleteById(id);
    }

    @Override
    public HarvestDTO getHarvestById(Long id) {
        // Récupérer la récolte par son ID
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found with ID: " + id));

        // Convertir l'entité en DTO
        return harvestMapper.harvestToDTO(harvest);
    }
}
