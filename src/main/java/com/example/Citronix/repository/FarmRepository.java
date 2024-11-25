package com.example.Citronix.repository;


import com.example.Citronix.dto.farm.FarmSearchCriteria;
import com.example.Citronix.model.Farm;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface FarmRepository extends JpaRepository<Farm, Long>, FarmRepositoryCustom {
    boolean existsByName(String name);




}
