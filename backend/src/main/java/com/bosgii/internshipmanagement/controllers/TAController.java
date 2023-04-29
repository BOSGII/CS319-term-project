package com.bosgii.internshipmanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.services.TAService;

@RestController
@RequestMapping("/api")
public class TAController {
	private TAService tAService;

	public TAController(TAService tAService) {
		this.tAService = tAService;
	}
}
