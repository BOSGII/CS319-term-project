package com.bosgii.internshipmanagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long>{
	List<Internship> getInternshipsByStudentId(Long studentId);
}