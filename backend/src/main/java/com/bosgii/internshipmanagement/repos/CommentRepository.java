package com.bosgii.internshipmanagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> getAllByVersionId(Long versionId);
}