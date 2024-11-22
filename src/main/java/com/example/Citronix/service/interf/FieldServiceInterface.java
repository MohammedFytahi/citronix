package com.example.Citronix.service.interf;

import com.example.Citronix.dto.field.FieldCreateDTO;
import com.example.Citronix.dto.field.FieldUpdateDTO;
import com.example.Citronix.model.Field;

public interface FieldServiceInterface {
    public void createField(FieldCreateDTO fieldCreateDTO);

    public Field updateField(FieldUpdateDTO fieldUpdateDTO);
}
