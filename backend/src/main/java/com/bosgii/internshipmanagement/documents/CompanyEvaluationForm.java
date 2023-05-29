package com.bosgii.internshipmanagement.documents;

import com.bosgii.internshipmanagement.entities.CompanyEvaluation;

import javax.persistence.*;

@Entity
@Table(name = "CompanyEvaluationForm")
public class CompanyEvaluationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    CompanyEvaluation companyEvaluation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyEvaluation getCompanyEvaluation() {
        return companyEvaluation;
    }

    public void setCompanyEvaluation(CompanyEvaluation companyEvaluation) {
        this.companyEvaluation = companyEvaluation;
    }
}
