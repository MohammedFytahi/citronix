package com.example.Citronix.service.interf;

        import com.example.Citronix.dto.harvest.HarvestCreateDTO;
        import com.example.Citronix.dto.harvest.HarvestDTO;
        import com.example.Citronix.dto.harvest.HarvestUpdateDTO;
        import com.example.Citronix.model.Harvest;

public interface HarvestServiceInterface {


    Harvest createHarvest(HarvestCreateDTO harvestCreateDTO, Long fieldId);


    Harvest updateHarvest(Long id, HarvestUpdateDTO harvestUpdateDTO);


    void deleteHarvest(Long id);


    HarvestDTO getHarvestById(Long id);
}
