package com.example.Citronix.repository;

import com.example.Citronix.enums.Season;
import com.example.Citronix.model.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
     Optional<Harvest> findBySeasonAndHarvestDetails_Tree_Field_Id(Season season, Long fieldId);
}
