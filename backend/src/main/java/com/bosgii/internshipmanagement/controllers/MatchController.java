package com.bosgii.internshipmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.enums.MatchType;
import com.bosgii.internshipmanagement.services.MatchService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/match")
    public ResponseEntity<String> matchInstuctorsWithInternships(@RequestParam MatchType matchType) {
        if (matchService.matchInstructorsWithInternships(matchType)) {
            return ResponseEntity.ok("Success!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Number of internships are greater than the total capacity of instructors. Please increase capacity of instructors from Instructors section.");
        }
    }
}
