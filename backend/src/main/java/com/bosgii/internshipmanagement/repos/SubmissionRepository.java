package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
	Optional<Submission> findSubmissionByInternshipId(Long internshipId);
}
