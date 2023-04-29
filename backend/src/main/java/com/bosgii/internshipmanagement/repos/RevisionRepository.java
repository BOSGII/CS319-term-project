package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Revision;

public interface RevisionRepository extends JpaRepository<Revision, Long>{

}
