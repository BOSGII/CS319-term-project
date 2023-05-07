package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Comment;
import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.repos.CommentRepository;
import com.bosgii.internshipmanagement.requests.AddCommentRequest;
import com.bosgii.internshipmanagement.requests.ChangeCommentRequest;

@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	private VersionService versionService;
	
	public CommentService(CommentRepository commentRepository, VersionService versionService) {
		this.commentRepository = commentRepository;
		this.versionService = versionService;
	}

	public List<Comment> getAllCommentsOfAVersion(Long versionId) {
		return commentRepository.getAllByVersionId(versionId);
	}

	public Comment addCommentOnAVersion(Long versionId, AddCommentRequest req) {
		// TODO: Check if it is the same comment
		Optional<Version> currentVersion = versionService.getVersionById(versionId);
		Comment toBeAdded = new Comment();
		if (currentVersion.isPresent()) {
			toBeAdded.setVersion(currentVersion.get());
		} else {
			// exception
		}
		toBeAdded.setMessage(req.getMessage());

		return commentRepository.save(toBeAdded);
	}

	public Comment changeComment(Long commentId, ChangeCommentRequest req) {
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isPresent()) {
			Comment toBeUpdated = opt.get();
			toBeUpdated.setMessage(req.getMessage());
			return commentRepository.save(toBeUpdated);
		}

		return null;
	}

	public Comment deleteComment(Long commentId) {
		Optional<Comment> opt = commentRepository.findById(commentId);	
		commentRepository.deleteById(commentId);
		return opt.get();
	}
}
