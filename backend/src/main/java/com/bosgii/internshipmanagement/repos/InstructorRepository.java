package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}