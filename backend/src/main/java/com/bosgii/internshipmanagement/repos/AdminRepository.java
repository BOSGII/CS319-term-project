package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Long id);
}
