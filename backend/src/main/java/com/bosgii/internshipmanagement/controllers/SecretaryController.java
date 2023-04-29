package com.bosgii.internshipmanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.services.SecretaryService;

@RestController
@RequestMapping("/api")
public class SecretaryController {
	private SecretaryService secretaryService;
	
	public SecretaryController(SecretaryService secretaryService) {
		this.secretaryService = secretaryService;
	}
}
