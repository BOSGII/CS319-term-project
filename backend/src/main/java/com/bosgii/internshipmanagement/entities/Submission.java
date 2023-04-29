package com.bosgii.internshipmanagement.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

enum SubmissionStatus {
	UNDER_FORMAT_CHECK,
	UNDER_CONTENT_EVALUATION,
	CLOSED
}

@Entity
@Table(name="Submission")
@Data
public class Submission {
	@Id
	Long id;
	
	@OneToOne
	Internship internship;
	
	@ManyToOne
	Instructor gradedBy;
	
	@ManyToOne
	TA formatCheckPerformedBy;
	
	SubmissionStatus status;
}
