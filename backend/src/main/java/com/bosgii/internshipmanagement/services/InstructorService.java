package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;
import com.bosgii.internshipmanagement.exceptions.UserIdExistsException;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.UserRepository;
import com.bosgii.internshipmanagement.requests.AddInstructorRequest;
import com.bosgii.internshipmanagement.requests.ChangeInstructorRequest;

@Service
public class InstructorService {

	private InstructorRepository instructorRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;



	public InstructorService(InstructorRepository instructorRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Instructor> getAllInstructors(Optional<Boolean> available) {
		if (available.isPresent()) {
			return instructorRepository.findAllAvailable();
		}
		return instructorRepository.findAll();
	}

	public Instructor createInstructor(AddInstructorRequest req) throws InvalidMailAddressException, UserIdExistsException {
		Instructor newInstructor = new Instructor();
		if (userRepository.existsById(req.getId())) {
			throw new UserIdExistsException(req.getId());
		}
		newInstructor.setId(req.getId());
		newInstructor.setFullName(req.getFullName());
		newInstructor.setMail(req.getMail());
		newInstructor.setDepartment(req.getDepartment());
		newInstructor.setMaxNumOfInternships(req.getMaxNumOfInternships());

		newInstructor.setNumOfAssignedInternships(0);
		newInstructor.setRole("instructor");
		newInstructor.setCompleted(0);
		//note that we have random string generater method to cretae a random passsword. this is just for test and simplicity
        newInstructor.setPassword(passwordEncoder.encode(newInstructor.getId().toString()));
        InternshipService.sendEmail(newInstructor.getMail(), newInstructor.getId().toString());
		return instructorRepository.save(newInstructor);
	}

	public Instructor changeInstructorDetails(Long instructorId, ChangeInstructorRequest req) throws InvalidMailAddressException {
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
		toBeUpdated.setMaxNumOfInternships(req.getMaxNumOfInternships());

		return instructorRepository.save(toBeUpdated);
	}

	public void deleteInstructor(Long instructorId) {
		instructorRepository.deleteById(instructorId);
	}
}
