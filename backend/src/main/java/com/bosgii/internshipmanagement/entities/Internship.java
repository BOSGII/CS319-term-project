package com.bosgii.internshipmanagement.entities;

import java.util.Date;

import com.bosgii.internshipmanagement.enums.InternshipType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Internship")
public class Internship {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	Student student;
	
	@ManyToOne
	Company company;
	
	@ManyToOne
	Supervisor supervisor;
	
	Date startDate;
	Date endDate;
	InternshipType type;
	int numOfDrafts;
	// TODO: company evaluation form
	
	public Long getInternshipId() {
		return id;
	}
	public void setInternshipId(Long internshipId) {
		this.id = internshipId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Supervisor getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public InternshipType getType() {
		return type;
	}
	public void setType(InternshipType type) {
		this.type = type;
	}
	public int getNumOfDrafts() {
		return numOfDrafts;
	}
	public void setNumOfDrafts(int numOfDrafts) {
		this.numOfDrafts = numOfDrafts;
	}
}
