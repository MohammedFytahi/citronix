package com.example.Citronix.repository;

import com.example.Citronix.model.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, Long> {


    List<HarvestDetail> findByHarvestId(Long harvestId);

    List<HarvestDetail> findByTreeId(Long treeId);
}
