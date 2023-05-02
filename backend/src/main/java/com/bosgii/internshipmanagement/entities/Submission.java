package com.bosgii.internshipmanagement.entities;
import java.util.List;

import com.bosgii.internshipmanagement.enums.SubmissionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Submission")
public class Submission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	Instructor gradedBy;
	
	@ManyToOne
	TA formatCheckPerformedBy;

	SubmissionStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instructor getGradedBy() {
		return gradedBy;
	}

	public void setGradedBy(Instructor gradedBy) {
		this.gradedBy = gradedBy;
	}

	public TA getFormatCheckPerformedBy() {
		return formatCheckPerformedBy;
	}

	public void setFormatCheckPerformedBy(TA formatCheckPerformedBy) {
		this.formatCheckPerformedBy = formatCheckPerformedBy;
	}

	public SubmissionStatus getStatus() {
		return status;
	}

	public void setStatus(SubmissionStatus status) {
		this.status = status;
	}

}
