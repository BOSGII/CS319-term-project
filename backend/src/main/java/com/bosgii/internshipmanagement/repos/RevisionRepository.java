package com.bosgii.internshipmanagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Revision;

public interface RevisionRepository extends JpaRepository<Revision, Long>{
    List<Revision> getRevisionsByInternshipId(int internshipId);
}
