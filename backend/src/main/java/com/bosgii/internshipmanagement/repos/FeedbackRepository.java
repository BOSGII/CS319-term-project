package com.bosgii.internshipmanagement.repos;

import com.bosgii.internshipmanagement.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findByVersionId(Long id);
}
