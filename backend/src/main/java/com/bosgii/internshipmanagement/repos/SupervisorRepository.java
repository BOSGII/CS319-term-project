package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.entities.Company;
import com.bosgii.internshipmanagement.entities.Supervisor;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long>{

	Optional<Supervisor> findByNameAndUniversity(String name, String university);

}