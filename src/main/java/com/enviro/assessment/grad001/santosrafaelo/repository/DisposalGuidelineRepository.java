package com.enviro.assessment.grad001.santosrafaelo.repository;

import com.enviro.assessment.grad001.santosrafaelo.model.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline,Long> {
}
