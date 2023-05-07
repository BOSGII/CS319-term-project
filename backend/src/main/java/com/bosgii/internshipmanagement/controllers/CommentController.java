package com.bosgii.internshipmanagement.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.Comment;
import com.bosgii.internshipmanagement.requests.AddCommentRequest;
import com.bosgii.internshipmanagement.requests.ChangeCommentRequest;
import com.bosgii.internshipmanagement.services.CommentService;


@RestController
@RequestMapping("/api")
public class CommentController {
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/comments")
	public List<Comment> getAllCommentsOfAVersion(@RequestParam Long versionId) {
		return commentService.getAllCommentsOfAVersion(versionId);
	}

	@PostMapping("/comments")
	public Comment addCommentOnAVersion(@RequestParam Long versionId, @RequestBody AddCommentRequest req) {
		return commentService.addCommentOnAVersion(versionId, req);
	}

	@PutMapping("/comments/{commentId}")
	public Comment changeComment(@PathVariable Long commentId,
			@RequestBody ChangeCommentRequest req) {
		return commentService.changeComment(commentId, req);
	}

	@DeleteMapping("/comments/{commentId}")
	public Comment deleteComment(@PathVariable Long commentId) {
		return commentService.deleteComment(commentId);
	}
}
