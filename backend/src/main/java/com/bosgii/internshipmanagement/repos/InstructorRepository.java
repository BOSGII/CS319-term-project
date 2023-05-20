package com.bosgii.internshipmanagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bosgii.internshipmanagement.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{
    @Query(value = "SELECT * FROM Abstract_User u, Evaluator e, Instructor i WHERE u.id = e.id AND e.id = i.id AND num_of_assigned_internships < max_num_of_internships", nativeQuery = true)
    public List<Instructor> findAllAvailable();
}