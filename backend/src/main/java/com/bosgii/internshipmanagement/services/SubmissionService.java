package com.bosgii.internshipmanagement.services;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.enums.SubmissionStatus;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;
import com.bosgii.internshipmanagement.requests.AddSubmissionRequest;
import com.bosgii.internshipmanagement.requests.ChangeSubmissionRequest;

@Service
public class SubmissionService {
	
	private SubmissionRepository submissionRepository;
	private InternshipService internshipService;
	
	public SubmissionService(SubmissionRepository submissionRepository, InternshipService internshipService) {
		this.submissionRepository = submissionRepository;
		this.internshipService = internshipService;
	}
	

	public Optional<Submission> findSubmission(Optional<Long> internshipId, Optional<Long> studentId,
			Optional<InternshipType> type) {
		if(internshipId.isPresent()) {
			return submissionRepository.findSubmissionByInternshipId(internshipId.get());
		} else if(studentId.isPresent() && type.isPresent()) {
			return findSubmissionByStudentIdAndInternshipType(studentId.get(), type.get());
		}
		
		return Optional.empty();
	}
	
	public Submission getOneSubmissionById(Long SubmissionId) {
		return submissionRepository.findById(SubmissionId).orElse(null);
	}
	
	private Optional<Submission> findSubmissionByStudentIdAndInternshipType(Long studentId, InternshipType internshipType){
		Optional<Internship> i = internshipService.findInternshipByStudentIdAndType(studentId, internshipType);
		if(i.isPresent()) {
			return findSubmissionOfAnInternship(i.get().getId());
		}
		
		return Optional.empty();
	}

	public Optional<Submission> findSubmissionOfAnInternship(Long internshipId) {
		return submissionRepository.findSubmissionByInternshipId(internshipId);
	}

	public Submission addSubmissionOnAnInternship(Long internshipId, AddSubmissionRequest req) {
		Internship internship = internshipService.getOneInternshipById(internshipId);

		if(internship == null)
			return null;

		Submission submission = new Submission();
		submission.setStatus(SubmissionStatus.UNDER_FORMAT_CHECK);//dikkat 
		submission.setInternship(internship);
		return submissionRepository.save(submission);
	}

	public Submission changeSubmission(Long submissionId, ChangeSubmissionRequest req) {
		Optional<Submission> submission = submissionRepository.findById(submissionId);

		if(submission.isPresent()){
			Submission foundsSubmission = submission.get();
			foundsSubmission.setStatus(req.getSubmissionStatus());
			submissionRepository.save(foundsSubmission);
			return foundsSubmission;
		}

		return null;
	}

	public Submission deleteSubmission(Long submissionId) {
		
		Optional<Submission> submission = submissionRepository.findById(submissionId);
		submissionRepository.deleteById(submissionId);
		return submission.get();
		
	}


}
