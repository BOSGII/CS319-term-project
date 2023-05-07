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

import com.bosgii.internshipmanagement.entities.Submission;
import com.bosgii.internshipmanagement.requests.AddSubmissionRequest;
import com.bosgii.internshipmanagement.requests.ChangeSubmissionRequest;
import com.bosgii.internshipmanagement.services.SubmissionService;

@RestController
@RequestMapping("/api")
public class SubmissionController {
	private final SubmissionService submissionService;

	public SubmissionController(SubmissionService submissionService) {
		this.submissionService = submissionService;
	}
	
	@GetMapping("/submissions")
	public List<Submission> getSubmissionOfAnInternship(@RequestParam Long internshipId) {
		return submissionService.getSubmissionOfAnInternship(internshipId);
	}

	@PostMapping("/submissions")
	public Submission addSubmissionOnAnInternship(@RequestParam Long internshipId, @RequestBody AddSubmissionRequest req) {
		return submissionService.addSubmissionOnAnInternship(internshipId, req);
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
}
