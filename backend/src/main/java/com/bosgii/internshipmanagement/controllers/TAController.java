package com.bosgii.internshipmanagement.controllers;

import java.util.List;

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
	public TA createTA(@RequestBody AddTARequest req) {
		return taService.createTA(req);
	}

	@PutMapping("tas/{taId}")
	public TA changeTADetails(@PathVariable Long taId,
			@RequestBody ChangeTARequest req) {
		return taService.changeTADetails(taId, req);
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
