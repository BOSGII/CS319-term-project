package com.bosgii.internshipmanagement.entities;
import java.util.List;
import java.util.Set;

import com.bosgii.internshipmanagement.enums.VersionStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Version")
public class Version {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	Submission submission;
	
	VersionStatus status;
	
	// TODO: add file property (maybe only path?)

	/* 
	@OneToMany(mappedBy="revision", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Comment> comments;
	*/

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

	public VersionStatus getStatus() {
		return status;
	}

	public void setStatus(VersionStatus status) {
		this.status = status;
	}
}
