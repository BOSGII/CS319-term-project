package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long>{

}