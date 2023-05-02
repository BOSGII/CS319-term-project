package com.bosgii.internshipmanagement.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Student;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.requests.AddInternshipRequest;
import com.bosgii.internshipmanagement.requests.AddStudentRequest;
import com.bosgii.internshipmanagement.services.SecretaryService;

@RestController
@RequestMapping("/api")
public class SecretaryController {
	private SecretaryService secretaryService;
	
	public SecretaryController(SecretaryService secretaryService) {
		this.secretaryService = secretaryService;
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return secretaryService.getAllStudents();
	}
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody AddStudentRequest newStudentRequest) {
		return secretaryService.addStudentIfNotExists(newStudentRequest);
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable Long studentId) {
		return secretaryService.getStudentById(studentId);
	}
	
	// TODO: add put mapping for /students/{studentId}
	
	@GetMapping("/students/{studentId}/internships")
	public List<Internship> getInternshipsOfAStudent(@PathVariable Long studentId){
		return secretaryService.getInternshipsByStudentId(studentId);
	}
	
	@PostMapping("/students/{studentId}/internships")
	public Internship addInternshipForAStudent(@PathVariable Long studentId, @RequestBody AddInternshipRequest newInternshipRequest){
		return secretaryService.addInternshipInfoIfNotExist(studentId, newInternshipRequest);
	}
	
	@GetMapping("/students/{studentId}/internships/{internshipType}")
	public Internship getInternship(@PathVariable Long studentId, @PathVariable InternshipType internshipType) {
		return secretaryService.getInternshipOfAStudentByType(studentId, internshipType);
	}
	
	// TODO: add put mapping for /students/{studentId}/internships/{internshipId}
}
