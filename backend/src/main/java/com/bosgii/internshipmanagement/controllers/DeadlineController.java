package com.bosgii.internshipmanagement.controllers;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.services.DeadlineService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class DeadlineController {
    private final DeadlineService deadlineService;

    public DeadlineController(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @PostMapping("/deadline")
    public ResponseEntity<String> setInitialDeadline(@RequestBody Date deadline) {
        try {
            deadlineService.setInitialDeadline(deadline);
            return ResponseEntity.ok("Deadline created successfully.");
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
