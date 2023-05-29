package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	Optional<Company> findByEmail(String companyEmail);
}
