package com.example.Citronix.service.interf;

import com.example.Citronix.dto.FarmCreateDTO;
import com.example.Citronix.dto.FarmUpdateDTO;

public interface FarmeServiceInterface {
    public void addFarm(FarmCreateDTO farmCreateDTO);

    public void updateFarm(Long farmId, FarmUpdateDTO farmUpdateDTO);
}
