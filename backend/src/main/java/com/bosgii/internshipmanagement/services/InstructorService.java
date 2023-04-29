package com.bosgii.internshipmanagement.services;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.repos.SubmissionRepository;

@Service
public class InstructorService {

	private SubmissionRepository submissionRepository;

	public InstructorService(SubmissionRepository submissionRepository) {
		this.submissionRepository = submissionRepository;
	}
}
