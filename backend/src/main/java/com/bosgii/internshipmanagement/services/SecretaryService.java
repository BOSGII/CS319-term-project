package com.bosgii.internshipmanagement.services;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;
import com.bosgii.internshipmanagement.repos.TARepository;

@Service
public class SecretaryService {

	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private TARepository tARepository;
	private InternshipRepository internshipRepository;
	private SubmissionRepository submissionRepository;
	
	public SecretaryService(StudentRepository studentRepository, InstructorRepository instructorRepository,
			TARepository tARepository, InternshipRepository internshipRepository,
			SubmissionRepository submissionRepository) {
		this.studentRepository = studentRepository;
		this.instructorRepository = instructorRepository;
		this.tARepository = tARepository;
		this.internshipRepository = internshipRepository;
		this.submissionRepository = submissionRepository;
	}
	
	
}
