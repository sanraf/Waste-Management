package com.enviro.assessment.grad001.santosrafaelo.repository;

import com.enviro.assessment.grad001.santosrafaelo.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory,Long> {
}
