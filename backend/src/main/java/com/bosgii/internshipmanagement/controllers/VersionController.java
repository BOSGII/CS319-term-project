package com.bosgii.internshipmanagement.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.requests.AddVersionRequest;
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
	public List<Version> getAllVersionsOfASubmission(@RequestParam Long submissionId){
		return versionService.getAllVersionsOfASubmission(submissionId);
	}
	
	@PostMapping("/versions")
	public Version addVersionOnASubmission(@RequestParam Long submissionId, @RequestBody AddVersionRequest req) {
		return versionService.addVersionOnASubmission(submissionId, req);
	}
	
	@PutMapping("/versions/{versionId}")
	public Version changeVersion(@PathVariable Long versionId, @RequestBody ChangeVersionRequest req) {
		return versionService.changeVersion(versionId, req);
	}
	
	@DeleteMapping("/versions/{versionId}")
	public Version deleteVersion(@PathVariable Long versionId) {
		return versionService.deleteVersion(versionId);
	}
}
