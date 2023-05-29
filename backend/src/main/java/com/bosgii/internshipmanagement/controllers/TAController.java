package com.bosgii.internshipmanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.TA;
import com.bosgii.internshipmanagement.requests.AddTARequest;
import com.bosgii.internshipmanagement.requests.ChangeTARequest;
import com.bosgii.internshipmanagement.services.TAService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class TAController {
    private final TAService taService;

    public TAController(TAService taService) {
        this.taService = taService;
    }

    @GetMapping("/tas")
    public List<TA> getAllTAs() {
        return taService.getAllTAs();
    }

    @PostMapping("/tas")
    public ResponseEntity<String> createTA(@RequestBody AddTARequest req) {
        try {
            taService.createTA(req);
            return ResponseEntity.ok("TA created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("tas/{taId}")
    public ResponseEntity<String> changeTADetails(@PathVariable Long taId,
            @RequestBody ChangeTARequest req) {
        try {
            taService.changeTADetails(taId, req);
            return ResponseEntity.ok("TA changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/tas/{taId}")
    public void deleteTA(@PathVariable Long taId) {
        taService.deleteTA(taId);
    }

    @PostMapping("/tas/match")
    public void matchTAs() {
        taService.matchTAs();
    }
}
