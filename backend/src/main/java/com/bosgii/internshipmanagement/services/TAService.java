package com.bosgii.internshipmanagement.services;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.repos.SubmissionRepository;

@Service
public class TAService {

	private SubmissionRepository submissionRepository;

	public TAService(SubmissionRepository submissionRepository) {
		this.submissionRepository = submissionRepository;
	}
}
