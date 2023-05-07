package com.bosgii.internshipmanagement.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.enums.InternshipType;

public interface InternshipRepository extends JpaRepository<Internship, Long>{
	List<Internship> getAllByStudentId(Long studentId);
	List<Internship> getAllByInstructorId(Long instructorId);
	Optional<Internship> findByStudentIdAndType(Long studentId, InternshipType type);
}