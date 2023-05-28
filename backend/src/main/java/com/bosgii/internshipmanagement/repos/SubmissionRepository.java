package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bosgii.internshipmanagement.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
	Optional<Submission> findSubmissionByInternshipId(Long internshipId);

	@Query(value = "SELECT internship_id FROM Submission WHERE id = :submissionId", nativeQuery = true)
    Long getInternshipId(@Param("submissionId") Long submissionId);
}
