package com.bosgii.internshipmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.requests.AddInstructorRequest;
import com.bosgii.internshipmanagement.requests.ChangeInstructorRequest;

@Service
public class InstructorService {

	private InstructorRepository instructorRepository;

	public InstructorService(InstructorRepository submissionRepository) {
		this.instructorRepository = submissionRepository;
	}

	public List<Instructor> getAllInstructors() {
		// TODO Auto-generated method stub
		return null;
	}

	public Instructor createInstructor(AddInstructorRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Instructor changeInstructorDetails(Long instructorId, ChangeInstructorRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Instructor deleteInstructor(Long instructorId) {
		// TODO Auto-generated method stub
		return null;
	}
}
