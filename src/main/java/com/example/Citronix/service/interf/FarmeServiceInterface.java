package com.example.Citronix.service.interf;

import com.example.Citronix.dto.farm.FarmCreateDTO;
import com.example.Citronix.dto.farm.FarmDTO;
import com.example.Citronix.dto.farm.FarmSearchCriteria;
import com.example.Citronix.dto.farm.FarmUpdateDTO;

import java.util.List;

public interface FarmeServiceInterface {


    void addFarm(FarmCreateDTO farmCreateDTO);


    void updateFarm(Long id, FarmUpdateDTO farmUpdateDTO);


    FarmDTO getFarm(Long id);

    void deleteFarm(Long id);


    List<FarmDTO> getAllFarms();
    List<FarmDTO> searchFarms(FarmSearchCriteria criteria);
}
