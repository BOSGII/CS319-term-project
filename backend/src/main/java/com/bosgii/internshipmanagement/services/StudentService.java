package com.bosgii.internshipmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.repos.CommentRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.RevisionRepository;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;

@Service
public class StudentService {
	private InternshipRepository internshipRepository;
	private CommentRepository commentRepository;
	private SubmissionRepository submissionRepository;
	private RevisionRepository revisionRepository;

	public StudentService(InternshipRepository internshipRepository, CommentRepository commentRepository,
							SubmissionRepository submissionRepository, RevisionRepository revisionRepository) {
		this.internshipRepository = internshipRepository;
		this.commentRepository = commentRepository;
		this.submissionRepository = submissionRepository;
		this.revisionRepository = revisionRepository;		
	}
	public List<Internship> getInternships(Long studentId){
		return internshipRepository.getInternshipsByStudentId(studentId);
	}
	public List<Submission> getSubmissions(Long internshipId){
		return submissionRepository.getSubmissionsByInternshipId(internshipId);
	}

}
