package com.bosgii.internshipmanagement.controllers;

import com.bosgii.internshipmanagement.entities.Secretary;
import com.bosgii.internshipmanagement.requests.AddSecretaryRequest;
import com.bosgii.internshipmanagement.requests.ChangeSecretaryRequest;
import com.bosgii.internshipmanagement.services.AdminService;
import com.bosgii.internshipmanagement.services.SecretaryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:3000")
@RestController

@RequestMapping("/api")
public class AdminController {

    private AdminService adminService;
    private SecretaryService secretaryService;


    public AdminController(SecretaryService secretaryService, AdminService adminService) {

        this.secretaryService = secretaryService;
        this.adminService = adminService;
    }

    @GetMapping("/secretaries")
    public List<Secretary> getAllTSecretaries() {
        return secretaryService.getAllSecretaries();
    }

    @PostMapping("/secretaries")
    public ResponseEntity<String> createSecretary(@RequestBody AddSecretaryRequest req) {
        try {
            adminService.createSecretary(req);
            return ResponseEntity.ok("Secretary created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/secretaries/{secretaryId}")
    public void deleteSecretary(@PathVariable Long secretaryId) {
        adminService.deleteSecretary(secretaryId);
    }

    @PutMapping("/secretaries/{secretaryId}")
	public ResponseEntity<String> changeSecretaryDetails(@PathVariable Long secretaryId,
			@RequestBody ChangeSecretaryRequest req) {
				try {
					secretaryService.changeSecretary(secretaryId, req);
					return ResponseEntity.ok("Secretary changed successfully.");
				}
				catch(Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
				}
	}
}
