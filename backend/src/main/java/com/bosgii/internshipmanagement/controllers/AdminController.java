package com.bosgii.internshipmanagement.controllers;


import com.bosgii.internshipmanagement.entities.Secretary;
import com.bosgii.internshipmanagement.entities.TA;
import com.bosgii.internshipmanagement.requests.AddSecretaryRequest;
import com.bosgii.internshipmanagement.requests.AddTARequest;
import com.bosgii.internshipmanagement.services.AdminService;
import com.bosgii.internshipmanagement.services.SecretaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private AdminService adminService;
    private SecretaryService secretaryService;
    public AdminController(SecretaryService secretaryService, AdminService adminService){
        this.secretaryService = secretaryService;
        this.adminService = adminService;
    }

    @GetMapping("/secretaries")
    public List<Secretary> getAllTSecretaries() {
        return secretaryService.getAllSecretaries();
    }

    @PostMapping("/secretaries")
    public Secretary createSecretary(@RequestBody AddSecretaryRequest req) {
        return adminService.createSecretary(req);
    }

    @DeleteMapping("/secretaries/{secId}")
    public void deleteSecretary(@PathVariable Long secId) {
        adminService.deleteSecretary(secId);
    }
}
