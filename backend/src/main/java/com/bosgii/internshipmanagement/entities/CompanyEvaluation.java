package com.bosgii.internshipmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CompanyEvaluation")
public class CompanyEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
