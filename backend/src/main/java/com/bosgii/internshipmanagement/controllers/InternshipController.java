package com.bosgii.internshipmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.requests.AddInternshipRequest;
import com.bosgii.internshipmanagement.requests.ChangeInternshipRequest;
import com.bosgii.internshipmanagement.services.InternshipService;

@RestController
@RequestMapping("/api")
public class InternshipController {
	private final InternshipService internshipService;

	public InternshipController(InternshipService internshipService) {
		this.internshipService = internshipService;
	}
	
	@GetMapping("/internships")
	public List<Internship> getAllInternships(@RequestParam Optional<Long> studentId, @RequestParam Optional<Long> instructorId){
		return internshipService.getAllInternships(studentId, instructorId);
	}
	
	@PostMapping("/internships")
	public Internship addInternship(@RequestBody AddInternshipRequest req) {
		return internshipService.addInternship(req);
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
