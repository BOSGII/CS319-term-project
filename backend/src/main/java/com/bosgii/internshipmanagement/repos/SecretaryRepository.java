package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.entities.Secretary;

public interface SecretaryRepository extends JpaRepository<Secretary, Long> {
    Optional<Secretary> findById(Long id);
}