package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.documents.CompanyEvaluationForm;

public interface CompanyEvaluationFormRepository extends JpaRepository<CompanyEvaluationForm, Long>{
    CompanyEvaluationForm findByCompanyEvaluationId(Long id);
}
