package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.entities.TA;

public interface TARepository extends JpaRepository<TA, Long> {
    Optional<TA> findById(Long id);
}
