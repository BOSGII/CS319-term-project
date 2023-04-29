package com.bosgii.internshipmanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.services.AdminService;

@RestController
@RequestMapping("/api")
public class AdminController {
	private AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
}