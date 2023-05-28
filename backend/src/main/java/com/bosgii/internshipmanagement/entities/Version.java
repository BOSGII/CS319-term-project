package com.bosgii.internshipmanagement.entities;

import com.bosgii.internshipmanagement.enums.VersionStatus;

import javax.persistence.*;
/* 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
*/

@Entity
@Table(name = "Version")
public class Version {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne
	Submission submission;

	VersionStatus status;

	int versionNumber;

	String reportFileName;
	String feedbackFileName;

	boolean isFeedbackFileProvided;
	boolean areCommentsProvided;

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

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	public boolean getIsFeedbackFileProvided() {
		return isFeedbackFileProvided;
	}

	public void setIsFeedbackFileProvided(boolean isFeedbackFileProvided) {
		this.isFeedbackFileProvided = isFeedbackFileProvided;
	}

	public boolean getAreCommentsProvided() {
		return areCommentsProvided;
	}

	public void setAreCommentsProvided(boolean areCommentsProvided) {
		this.areCommentsProvided = areCommentsProvided;
	}

	public void setFeedbackFileName(String feedbackFileName) {
		this.feedbackFileName = feedbackFileName;
	}

	public void setFeedbackFileProvided(boolean isFeedbackFileProvided) {
		this.isFeedbackFileProvided = isFeedbackFileProvided;
	}
	
	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getFeedbackFileName() {
		return feedbackFileName;
	}
}
