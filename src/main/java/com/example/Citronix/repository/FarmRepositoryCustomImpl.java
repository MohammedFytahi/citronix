package com.example.Citronix.repository;

import com.example.Citronix.dto.farm.FarmSearchCriteria;
import com.example.Citronix.model.Farm;
import com.example.Citronix.repository.FarmRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FarmRepositoryCustomImpl implements FarmRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Farm> searchFarms(FarmSearchCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> farm = query.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

         if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            predicates.add(cb.like(cb.lower(farm.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            predicates.add(cb.like(cb.lower(farm.get("location")), "%" + criteria.getLocation().toLowerCase() + "%"));
        }
        if (criteria.getMinArea() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farm.get("area"), criteria.getMinArea()));
        }
        if (criteria.getMaxArea() != null) {
            predicates.add(cb.lessThanOrEqualTo(farm.get("area"), criteria.getMaxArea()));
        }

         query.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}
