package com.bosgii.internshipmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CompanyEvaluation")
public class CompanyEvaluation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	int supervisorGrade;

    @ManyToOne
    Internship internship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSupervisorGrade() {
        return supervisorGrade;
    }

    public void setSupervisorGrade(int supervisorGrade) {
        this.supervisorGrade = supervisorGrade;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }
}
