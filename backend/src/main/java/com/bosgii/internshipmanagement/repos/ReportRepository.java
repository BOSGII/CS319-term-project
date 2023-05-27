package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.documents.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByVersionId(Long id);
}