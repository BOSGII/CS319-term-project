package com.bosgii.internshipmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Version;
import com.bosgii.internshipmanagement.repos.VersionRepository;
import com.bosgii.internshipmanagement.requests.AddVersionRequest;
import com.bosgii.internshipmanagement.requests.ChangeVersionRequest;

@Service
public class VersionService {

	private final VersionRepository versionRepository;
	
	public VersionService(VersionRepository versionRepository) {
		this.versionRepository = versionRepository;
	}
	
	public List<Version> getAllVersionsOfASubmission(Long submissionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Internship addVersionOnASubmission(Long submissionId, AddVersionRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Internship changeVersion(Long versionId, ChangeVersionRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Internship deleteVersion(Long versionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
