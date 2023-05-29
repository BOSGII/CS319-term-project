package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosgii.internshipmanagement.documents.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findByVersionId(Long versionId);
}
