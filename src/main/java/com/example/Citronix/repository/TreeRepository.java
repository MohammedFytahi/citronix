package com.example.Citronix.repository;

import com.example.Citronix.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreeRepository extends JpaRepository<Tree, Long> {
    List<Tree> findByFieldId(Long fieldId);
}
