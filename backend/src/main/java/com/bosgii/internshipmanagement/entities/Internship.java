package com.bosgii.internshipmanagement.entities;

import java.util.Date;

import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.enums.InternshipStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	Instructor instructor;
	
	@ManyToOne
	TA tA;
	
	@ManyToOne
	Company company;
	
	@ManyToOne
	Supervisor supervisor;

	int numOfVersions;

	InternshipStatus status;
	
	Date startDate;
	Date endDate;
	InternshipType type;

	Date deadline;
	
	// TODO: company evaluation form
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public InternshipStatus getStatus() {
		return status;
	}
	public void setStatus(InternshipStatus status) {
		this.status = status;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public TA gettA() {
		return tA;
	}
	public void settA(TA tA) {
		this.tA = tA;
	}
	public int getNumOfVersions() {
		return numOfVersions;
	}
	public void setNumOfVersions(int numOfVersions) {
		this.numOfVersions = numOfVersions;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
}
