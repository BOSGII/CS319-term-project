package com.bosgii.internshipmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.requests.AddCompanyEvaluationRequest;
import com.bosgii.internshipmanagement.requests.AddInternshipRequest;
import com.bosgii.internshipmanagement.requests.AssignRequest;
import com.bosgii.internshipmanagement.requests.ChangeInternshipRequest;
import com.bosgii.internshipmanagement.services.InternshipService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api")
public class InternshipController {
	private final InternshipService internshipService;

	public InternshipController(InternshipService internshipService) {
		this.internshipService = internshipService;
	}

	@PostMapping("/internships/{internshipId}/companyForm")
	public Internship uploadCompanyEvaluation(@PathVariable Long internshipId,
			@ModelAttribute AddCompanyEvaluationRequest supervisorGrade) {
		return internshipService.uploadCompanyEvaluation(internshipId, supervisorGrade);
	}

	@GetMapping("/internships")
	public List<Internship> getAllInternships(@RequestParam Optional<Long> studentId,
			@RequestParam Optional<Long> instructorId) {
		return internshipService.getAllInternships(studentId, instructorId);
	}

	@GetMapping("/internships/{internshipId}")
	public Optional<Internship> getInternship(@PathVariable Long internshipId) {
		return internshipService.getOneInternshipById(internshipId);
	}

	@PostMapping("/internships")
	public ResponseEntity<String> addInternship(@RequestBody AddInternshipRequest req) {
		try {
			internshipService.addInternship(req);
			return ResponseEntity.ok("Internship created successfully.");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/internships/{internshipId}")
	public Internship assignToDifferentInstructor(@PathVariable Long internshipId, @RequestBody AssignRequest req) {
		return internshipService.assignToDifferentInstructor(internshipId, req);
	}

	@PutMapping("/internships/{internshipId}")
	public Internship changeInternship(@PathVariable Long internshipId, @RequestBody ChangeInternshipRequest req) {
		return internshipService.changeInternship(internshipId, req);
	}

	@DeleteMapping("/internships/{internshipId}")
	public Internship deleteInternship(@PathVariable Long internshipId) {
		return internshipService.deleteInternship(internshipId);
	}

}
