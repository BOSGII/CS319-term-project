package com.bosgii.internshipmanagement.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.requests.AddVersionRequest;
import com.bosgii.internshipmanagement.requests.AskForRevisionRequest;
import com.bosgii.internshipmanagement.requests.ChangeVersionRequest;
import com.bosgii.internshipmanagement.services.VersionService;

@RestController
@RequestMapping("/api")
public class VersionController {
	private final VersionService versionService;

	public VersionController(VersionService versionService) {
		this.versionService = versionService;
	}
	
	@GetMapping("/versions")
	public Optional<Version> getOneVersion(@RequestParam Optional<Long> submissionId, @RequestParam Optional<Long> internshipId, @RequestParam int versionNumber){
		return versionService.getOneVersion(submissionId, internshipId, versionNumber);
	}

	@PostMapping("/versions")
	public Version addVersionOnASubmission(@RequestParam Long internshipId, @ModelAttribute AddVersionRequest req ){
		return versionService.addVersionOnASubmission(internshipId, req);
	}

	@PostMapping("/versions/{versionId}")
	public Version requestRevisionForVersion(@PathVariable Long versionId, @ModelAttribute AskForRevisionRequest req){
		return versionService.requestRevisionForVersion(versionId, req);
	}
	
	@PutMapping("/versions/{versionId}")
	public Version changeVersion(@PathVariable Long versionId, @RequestBody ChangeVersionRequest req) {
		return null;
	}
	
	@DeleteMapping("/versions/{versionId}")
	public Version deleteVersion(@PathVariable Long versionId) {
		return versionService.deleteVersion(versionId);
	}
}
