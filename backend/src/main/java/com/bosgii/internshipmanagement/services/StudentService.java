package com.bosgii.internshipmanagement.services;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.repos.InternshipRepository;

@Service
public class StudentService {
	private InternshipRepository internshipRepository;

	public StudentService(InternshipRepository internshipRepository) {
		this.internshipRepository = internshipRepository;
	}
}
