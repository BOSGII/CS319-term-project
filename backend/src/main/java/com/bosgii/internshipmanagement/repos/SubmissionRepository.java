package com.bosgii.internshipmanagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
    List<Submission> getSubmissionsByInternshipId(Long internshipId);
    List<Submission>getSubmissionsByInstructorId(int instructorId);
}
