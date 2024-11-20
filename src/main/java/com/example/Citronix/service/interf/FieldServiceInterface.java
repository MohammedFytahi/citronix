package com.example.Citronix.service.interf;

import com.example.Citronix.dto.FieldCreateDTO;
import com.example.Citronix.dto.FieldUpdateDTO;
import com.example.Citronix.model.Field;

public interface FieldServiceInterface {
    public void createField(FieldCreateDTO fieldCreateDTO);

    public Field updateField(FieldUpdateDTO fieldUpdateDTO);
}
