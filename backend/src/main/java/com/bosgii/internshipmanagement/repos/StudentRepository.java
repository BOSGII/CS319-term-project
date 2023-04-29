package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
