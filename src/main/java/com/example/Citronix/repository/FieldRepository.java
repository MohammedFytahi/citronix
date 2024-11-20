package com.example.Citronix.repository;

import com.example.Citronix.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field,Long> {
    long countByFarmId(Long farmId);
}
