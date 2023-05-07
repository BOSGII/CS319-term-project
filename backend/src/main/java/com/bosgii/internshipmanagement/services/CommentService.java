package com.bosgii.internshipmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Comment;
import com.bosgii.internshipmanagement.repos.CommentRepository;
import com.bosgii.internshipmanagement.requests.AddCommentRequest;
import com.bosgii.internshipmanagement.requests.ChangeCommentRequest;

@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public List<Comment> getAllCommentsOfAVersion(Long versionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comment addCommentOnAVersion(Long versionId, AddCommentRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comment changeComment(Long commentId, ChangeCommentRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Comment deleteComment(Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
