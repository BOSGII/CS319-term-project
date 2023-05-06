package com.bosgii.internshipmanagement.entities;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Revision")
public class Revision {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	Submission submission;

	@OneToMany(mappedBy="revision", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Submission getSubmission() {
		return submission;
	}

	public void setSubmission(Submission submission) {
		this.submission = submission;
	}
	
}
