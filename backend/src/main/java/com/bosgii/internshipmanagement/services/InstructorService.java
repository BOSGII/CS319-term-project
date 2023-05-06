package com.bosgii.internshipmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Comment;
import com.bosgii.internshipmanagement.entities.Revision;
import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.repos.CommentRepository;
import com.bosgii.internshipmanagement.repos.RevisionRepository;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;

@Service
public class InstructorService {

	private SubmissionRepository submissionRepository;
	private RevisionRepository revisionRepository;
	private CommentRepository commentRepository;
	public InstructorService(SubmissionRepository submissionRepository,
							RevisionRepository revisionRepository,
							CommentRepository commentRepository) {
		this.submissionRepository = submissionRepository;
		this.revisionRepository = revisionRepository;
		this.submissionRepository = submissionRepository;
	}
	public List<Submission> getSubmissions(int instructorId){
		return submissionRepository.getSubmissionsByInstructorId(instructorId);
	}
	public List<Comment> getComments(int revisionId){
		return commentRepository.getCommentsByRevisionId(revisionId);
	}
	public List<Revision> getRevisions(int internshipId){
		return revisionRepository.getRevisionsByInternshipId(internshipId);
	}
}
