package com.bosgii.internshipmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.repos.CommentRepository;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;
import com.bosgii.internshipmanagement.requests.AddCommentRequest;
import com.bosgii.internshipmanagement.requests.ChangeCommentRequest;

@Service
public class SubmissionService {
	
	private final SubmissionRepository submissionRepository;
	
	public SubmissionService(SubmissionRepository submissionRepository) {
		this.submissionRepository = submissionRepository;
	}
	
	public List<Submission> getSubmissionOfAnInternship(Long internshipId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Submission addSubmissionOnAnInternship(Long internshipId, AddCommentRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Submission changeSubmission(Long submissionId, ChangeCommentRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Submission deleteSubmission(Long submissionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
