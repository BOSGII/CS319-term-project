package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.entities.CompanyEvaluation;

public interface CompanyEvaluationRepository extends JpaRepository<CompanyEvaluation, Long>{
    Optional<CompanyEvaluation> findByInternshipId(Long internshipId);
}
