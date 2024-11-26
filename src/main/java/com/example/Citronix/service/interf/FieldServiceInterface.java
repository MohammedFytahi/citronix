package com.example.Citronix.service.interf;

import com.example.Citronix.dto.field.FieldCreateDTO;
import com.example.Citronix.dto.field.FieldDTO;
import com.example.Citronix.dto.field.FieldUpdateDTO;

import java.util.List;

public interface FieldServiceInterface {


    void createField(FieldCreateDTO fieldCreateDTO);


    FieldDTO updateField(FieldUpdateDTO fieldUpdateDTO);


    void deleteField(Long id);


    List<FieldDTO> getAllFields();
}
