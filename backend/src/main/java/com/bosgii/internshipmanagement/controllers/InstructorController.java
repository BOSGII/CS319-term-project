package com.bosgii.internshipmanagement.controllers;

import java.util.List;
import java.util.Optional;

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

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.requests.AddInstructorRequest;
import com.bosgii.internshipmanagement.requests.ChangeInstructorRequest;
import com.bosgii.internshipmanagement.services.InstructorService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api")
public class InstructorController {
	private InstructorService instructorService;

	public InstructorController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}

	@GetMapping("/instructors")
	public List<Instructor> getAllInstructors(@RequestParam Optional<Boolean> available) {
		return instructorService.getAllInstructors(available);
	}

	@PostMapping("/instructors")
	public Instructor createInstructor(@RequestBody AddInstructorRequest req) {
		return instructorService.createInstructor(req);
	}

	@PutMapping("instructors/{instructorId}")
	public Instructor changeInstructorDetails(@PathVariable Long instructorId,
			@RequestBody ChangeInstructorRequest req) {
		return instructorService.changeInstructorDetails(instructorId, req);
	}

	@DeleteMapping("/instructors/{instructorId}")
	public void deleteInstructor(@PathVariable Long instructorId) {
		instructorService.deleteInstructor(instructorId);
	}
}
