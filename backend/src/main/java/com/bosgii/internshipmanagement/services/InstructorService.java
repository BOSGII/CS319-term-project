package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.requests.AddInstructorRequest;
import com.bosgii.internshipmanagement.requests.ChangeInstructorRequest;

@Service
public class InstructorService {

	private InstructorRepository instructorRepository;

	public InstructorService(InstructorRepository instructorRepository) {
		this.instructorRepository = instructorRepository;
	}

	public List<Instructor> getAllInstructors() {
		return instructorRepository.findAll();
	}

	public Instructor createInstructor(AddInstructorRequest req) {
		Instructor newInstructor = new Instructor();
		newInstructor.setFullName(req.getFullName());
		newInstructor.setMail(req.getMail());
		newInstructor.setDepartment(req.getDepartment());
		newInstructor.setMaxNumOfSubmissions(req.getMaxNumOfSubmissions());

		newInstructor.setRole("instructor");
		newInstructor.setCompleted(0);
		
		return instructorRepository.save(newInstructor);
	}

	public Instructor changeInstructorDetails(Long instructorId, ChangeInstructorRequest req) {
		Instructor toBeUpdated;
		Optional<Instructor> opt = instructorRepository.findById(instructorId);
		
		if (opt.isPresent()) {
			toBeUpdated = opt.get();
		} else {
			toBeUpdated = new Instructor();
			toBeUpdated.setCompleted(0);
			toBeUpdated.setRole("instructor");
		}

		toBeUpdated.setFullName(req.getFullName());
		toBeUpdated.setMail(req.getMail());
		toBeUpdated.setDepartment(req.getDepartment());
		toBeUpdated.setMaxNumOfSubmissions(req.getMaxNumOfSubmissions());

		return instructorRepository.save(toBeUpdated);
	}

	public Instructor deleteInstructor(Long instructorId) {
		Optional<Instructor> opt = instructorRepository.findById(instructorId);
		instructorRepository.deleteById(instructorId);
		
		return opt.get();
	}
}
