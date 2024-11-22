package com.example.Citronix.service.impl;

import com.example.Citronix.dto.harvest.HarvestDetailCreateDTO;
import com.example.Citronix.mapper.HarvestDetailMapper;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.repository.HarvestDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestDetailService {

    @Autowired
    private HarvestDetailRepository harvestDetailRepository;
    @Autowired
    private HarvestDetailMapper harvestDetailMapper;


    public List<HarvestDetail> getDetailsByHarvest(Long harvestId) {
        return harvestDetailRepository.findByHarvestId(harvestId);
    }

    public List<HarvestDetail> getDetailsByTree(Long treeId) {
        return harvestDetailRepository.findByTreeId(treeId);
    }

    public HarvestDetail createHarvestDetail(HarvestDetailCreateDTO harvestDetailCreateDTO) {
        // Convertir le DTO en entité
        HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestDetailCreateDTO);

        // Sauvegarder dans la base de données
        return harvestDetailRepository.save(harvestDetail);
    }
}
