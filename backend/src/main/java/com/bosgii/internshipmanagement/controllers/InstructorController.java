package com.bosgii.internshipmanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.services.InstructorService;

@RestController
@RequestMapping("/api")
public class InstructorController {
	private InstructorService instructorService;
	
	public InstructorController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}
}
