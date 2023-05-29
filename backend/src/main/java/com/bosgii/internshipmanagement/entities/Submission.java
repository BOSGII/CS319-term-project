package com.bosgii.internshipmanagement.entities;

import com.bosgii.internshipmanagement.enums.SubmissionStatus;
import javax.persistence.*;

/* 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
*/
@Entity
@Table(name = "Submission")
public class Submission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne
	Internship internship;

	SubmissionStatus status;

	int numOfVersions;

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubmissionStatus getStatus() {
		return status;
	}

	public void setStatus(SubmissionStatus status) {
		this.status = status;
	}

	public int getNumOfVersions() {
		return numOfVersions;
	}

	public void setNumOfVersions(int numOfVersions) {
		this.numOfVersions = numOfVersions;
	}
}
