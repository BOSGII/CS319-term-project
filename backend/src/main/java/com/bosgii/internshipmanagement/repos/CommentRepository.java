package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}