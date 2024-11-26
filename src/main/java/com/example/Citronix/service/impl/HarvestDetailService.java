package com.example.Citronix.service.impl;

import com.example.Citronix.dto.harvest.HarvestDetailCreateDTO;
import com.example.Citronix.mapper.HarvestDetailMapper;
import com.example.Citronix.model.HarvestDetail;
import com.example.Citronix.repository.HarvestDetailRepository;
import lombok.RequiredArgsConstructor;
 import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HarvestDetailService {

     private final HarvestDetailRepository harvestDetailRepository;
     private final HarvestDetailMapper harvestDetailMapper;


    public List<HarvestDetail> getDetailsByHarvest(Long harvestId) {
        return harvestDetailRepository.findByHarvestId(harvestId);
    }

    public List<HarvestDetail> getDetailsByTree(Long treeId) {
        return harvestDetailRepository.findByTreeId(treeId);
    }


}
