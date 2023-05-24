package com.bosgii.internshipmanagement.controllers;

import java.util.List;
import java.util.Optional;

import com.bosgii.internshipmanagement.requests.GenerateFinalPDFRequest;
import com.bosgii.internshipmanagement.services.FinalPDFRequestService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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
import com.bosgii.internshipmanagement.requests.AssignRequest;
import com.bosgii.internshipmanagement.requests.ChangeInternshipRequest;
import com.bosgii.internshipmanagement.services.InternshipService;

@RestController
@RequestMapping("/api")
public class InternshipController {
	private final InternshipService internshipService;
	private final FinalPDFRequestService finalPDFRequestService;

	public InternshipController(InternshipService internshipService, FinalPDFRequestService finalPDFRequestService) {
		this.internshipService = internshipService;
		this.finalPDFRequestService = finalPDFRequestService;
	}

	@PostMapping("/internships/{internshipId}/generateFinal")
	public ResponseEntity<Resource> generateFinalPDF(@PathVariable Long internshipId, @RequestBody GenerateFinalPDFRequest req){
		return finalPDFRequestService.GenerateFinalPdf(internshipId,req);
	}
	@GetMapping("/internships")
	public List<Internship> getAllInternships(@RequestParam Optional<Long> studentId, @RequestParam Optional<Long> instructorId){
		return internshipService.getAllInternships(studentId, instructorId);
	}
	
	@GetMapping("/internships/{internshipId}")
	public Optional<Internship> getInternship(@PathVariable Long internshipId){
		return internshipService.getOneInternshipById(internshipId);
	}
	
	@PostMapping("/internships")
	public Internship addInternship(@RequestBody AddInternshipRequest req) {
		return internshipService.addInternship(req);
	}

	@PostMapping("/internships/{internshipId}")
	public Internship assignToDifferentInstructor(@PathVariable Long internshipId, @RequestBody AssignRequest req){
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
