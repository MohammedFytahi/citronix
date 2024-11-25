package com.example.Citronix.service.interf;

import com.example.Citronix.dto.field.FieldCreateDTO;
import com.example.Citronix.dto.field.FieldDTO;
import com.example.Citronix.dto.field.FieldUpdateDTO;

import java.util.List;

public interface FieldServiceInterface {

    /**
     * Crée un nouveau champ associé à une ferme.
     *
     * @param fieldCreateDTO les informations nécessaires pour créer un champ.
     */
    void createField(FieldCreateDTO fieldCreateDTO);

    /**
     * Met à jour les informations d'un champ existant.
     *
     * @param fieldUpdateDTO les informations mises à jour du champ.
     * @return le champ mis à jour.
     */
    FieldDTO updateField(FieldUpdateDTO fieldUpdateDTO);

    /**
     * Supprime un champ par son ID.
     *
     * @param id l'ID du champ à supprimer.
     */
    void deleteField(Long id);

    /**
     * Récupère tous les champs existants avec leurs détails associés.
     *
     * @return une liste de champs (DTO).
     */
    List<FieldDTO> getAllFields();
}
