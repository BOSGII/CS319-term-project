package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{

}
