package com.bosgii.internshipmanagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.entities.Version;

public interface VersionRepository extends JpaRepository<Version, Long>{
	List<Submission> getAllBySubmissionId(Long submissionId);
}