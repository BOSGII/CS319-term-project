package com.bosgii.internshipmanagement.controllers;

import java.util.Optional;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.requests.ChangeSubmissionRequest;
import com.bosgii.internshipmanagement.requests.FinalizeSubmissionRequest;
import com.bosgii.internshipmanagement.services.SubmissionService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api")
public class SubmissionController {
	private final SubmissionService submissionService;

	public SubmissionController(SubmissionService submissionService) {
		this.submissionService = submissionService;
	}

	@GetMapping("/submissions")
	public Optional<Submission> getSubmissionOfAnInternship(@RequestParam Optional<Long> internshipId,
			@RequestParam Optional<Long> studentId, @RequestParam Optional<InternshipType> internshipType) {
		return submissionService.findSubmission(internshipId, studentId, internshipType);
	}

	@PutMapping("/submissions/{submissionId}")
	public Submission changeSubmission(@PathVariable Long submissionId,
			@RequestBody ChangeSubmissionRequest req) {
		return submissionService.changeSubmission(submissionId, req);
	}

	@DeleteMapping("/submissions/{submissionId}")
	public Submission deleteSubmission(@PathVariable Long submissionId) {
		return submissionService.deleteSubmission(submissionId);
	}

	@PostMapping("/submissions/{submissionId}/finalize")
	public Submission finalizeSubmission(@PathVariable Long submissionId, @RequestBody FinalizeSubmissionRequest req){
		return submissionService.finalizeSubmission(submissionId, req);
	}

	@GetMapping("/submissions/{submissionId}/finalReport")
	public ResponseEntity<Resource> downloadFinalReport(@PathVariable Long submissionId){
		return submissionService.downloadFinalReport(submissionId);
	}
}
