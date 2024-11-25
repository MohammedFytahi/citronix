package com.example.Citronix.repository;

import com.example.Citronix.model.Farm;
import com.example.Citronix.dto.farm.FarmSearchCriteria;
import java.util.List;

public interface FarmRepositoryCustom {
    List<Farm> searchFarms(FarmSearchCriteria criteria);
}
