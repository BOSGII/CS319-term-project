package com.bosgii.internshipmanagement.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

enum InternshipType {
	CS299,
	CS399
}

@Entity
@Table(name="Internship")
@Data
public class Internship {
	@Id
	Long internshipId;
	
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
}
