package com.example.Citronix.service;

import com.example.Citronix.dto.HarvestCreateDTO;
import com.example.Citronix.mapper.HarvestMapper;
import com.example.Citronix.model.Harvest;
import com.example.Citronix.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private HarvestMapper harvestMapper;


    public Harvest createHarvest(HarvestCreateDTO harvestCreateDTO) {
        Harvest harvest = harvestMapper.toEntity(harvestCreateDTO);
        return harvestRepository.save(harvest);
    }
}
