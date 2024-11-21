package com.example.Citronix.service.interf;

import com.example.Citronix.dto.farm.FarmCreateDTO;
import com.example.Citronix.dto.farm.FarmUpdateDTO;

public interface FarmeServiceInterface {
    public void addFarm(FarmCreateDTO farmCreateDTO);

    public void updateFarm(Long farmId, FarmUpdateDTO farmUpdateDTO);
}
