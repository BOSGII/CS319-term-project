package com.bosgii.internshipmanagement.repos;

import com.bosgii.internshipmanagement.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByRequestID(Long requestID);
}
