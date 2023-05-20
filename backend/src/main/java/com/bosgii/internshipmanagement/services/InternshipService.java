package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Company;
import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Student;
import com.bosgii.internshipmanagement.entities.Supervisor;
import com.bosgii.internshipmanagement.enums.InternshipStatus;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.repos.CompanyRepository;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.SupervisorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.requests.AddInternshipRequest;
import com.bosgii.internshipmanagement.requests.ChangeInternshipRequest;

@Service
public class InternshipService {
	private final InternshipRepository internshipRepository;
	private final StudentRepository studentRepository;
	private final CompanyRepository companyRepository;
	private final SupervisorRepository supervisorRepository;
	private final InstructorRepository instructorRepository;
	
	public InternshipService(InternshipRepository internshipRepository, StudentRepository studentRepository,
			CompanyRepository companyRepository, SupervisorRepository supervisorRepository, InstructorRepository instructorRepository) {
		this.internshipRepository = internshipRepository;
		this.studentRepository = studentRepository;
		this.companyRepository = companyRepository;
		this.supervisorRepository = supervisorRepository;
		this.instructorRepository = instructorRepository;
	}

	public List<Internship> getAllInternships(Optional<Long> studentId, Optional<Long> instructorId) {
		// studentId and instructorId cannot be present at the same time
		if (studentId.isPresent()) {
			return internshipRepository.getAllByStudentId(studentId.get());
		} else if (instructorId.isPresent()) {
			return internshipRepository.getAllByInstructorId(instructorId.get());
		} 

		return internshipRepository.findAll();
	}

	public Optional<Internship> getOneInternshipById(Long internshipId) {
		return internshipRepository.findById(internshipId);
	}
	
	public Optional<Internship> findInternshipByStudentIdAndType(Long studentId, InternshipType type){
		return internshipRepository.findByStudentIdAndType(studentId, type);
	}

	public Internship addInternship(AddInternshipRequest req) {
		// check if the internship already exists
		Long studentId = req.getStudentId();
		InternshipType type = req.getType();
		Optional<Internship> internship = internshipRepository.findByStudentIdAndType(studentId, type);
		if (internship.isPresent()) {
			return internship.get();
		}

		// check if the student already exists
		Student st;
		Optional<Student> student = studentRepository.findById(req.getStudentId());
		if(student.isPresent()) {
			st = student.get();
		}
		else {
			st = new Student();
			st.setId(req.getStudentId());
			st.setFullName(req.getStudentFullName());
			st.setMail(req.getStudentMail());
			st.setRole("student");
			st.setDepartment(req.getStudentDepartment());
			studentRepository.save(st);
		}
		
		// check if the company already exists
		Company c;
		Optional<Company> company = companyRepository.findByNameAndEmail(req.getCompanyName(),
				req.getCompanyEmail());
		if (company.isPresent())
			c = company.get();
		else {
			c = new Company();
			c.setName(req.getCompanyName());
			c.setCompanyEmail(req.getCompanyEmail());
			companyRepository.save(c);
		}

		// check if the supervisor already exists
		Supervisor su;
		Optional<Supervisor> supervisor = supervisorRepository.findByNameAndUniversity(
				req.getSupervisorName(), req.getSupervisorUniversity());
		if (supervisor.isPresent())
			su = supervisor.get();
		else {
			su = new Supervisor();
			su.setName(req.getSupervisorName());
			su.setUniversity(req.getSupervisorUniversity());
			su.setEmail(req.getSupervisorMail());
			su.setGraduationDepartment(req.getSupervisorGraduationDepartment());
			su.setGraduationYear(req.getSupervisorGraduationYear());
			supervisorRepository.save(su);
		}
		
		// creating new internship
		Internship i = new Internship();
		i.setStudent(st);
		i.setCompany(c);
		i.setSupervisor(su);
		i.setStatus(InternshipStatus.WAITING_FOR_COMPANY_APPROVAL);
		
		i.setType(type);
		i.setStartDate(req.getStartDate());
		i.setEndDate(req.getEndDate());

		return internshipRepository.save(i);
	}

	public Internship changeInternship(Long internshipId, ChangeInternshipRequest req) {
		Internship toBeUpdated;
		Optional<Internship> opt = internshipRepository.findById(internshipId);
		if (opt.isPresent()) {
			toBeUpdated = opt.get();

			if(req.getInstructorId() == null){
				Instructor currentAssignedInstructor = toBeUpdated.getInstructor();
				if(currentAssignedInstructor != null) {
					currentAssignedInstructor.setNumOfAssignedInternships(currentAssignedInstructor.getNumOfAssignedInternships() - 1);
					instructorRepository.save(currentAssignedInstructor);
				}
				toBeUpdated.setInstructor(null);
			} else {
				Instructor currentAssignedInstructor = toBeUpdated.getInstructor();
				if(currentAssignedInstructor != null) {
					currentAssignedInstructor.setNumOfAssignedInternships(currentAssignedInstructor.getNumOfAssignedInternships() - 1);
					instructorRepository.save(currentAssignedInstructor);
				}
				Instructor newAssignedInstructor = instructorRepository.findById(req.getInstructorId()).get();
				newAssignedInstructor.setNumOfAssignedInternships(newAssignedInstructor.getNumOfAssignedInternships() + 1);
				instructorRepository.save(newAssignedInstructor);
				toBeUpdated.setInstructor(newAssignedInstructor);
			}

			// check if the company already exists
			Company c;
			Optional<Company> company = companyRepository.findByNameAndEmail(req.getCompanyName(),
					req.getCompanyEmail());
			if (company.isPresent())
				c = company.get();
			else {
				c = new Company();
				c.setName(req.getCompanyName());
				c.setCompanyEmail(req.getCompanyEmail());
				companyRepository.save(c);
			}

			// check if the supervisor already exists
			Supervisor su;
			Optional<Supervisor> supervisor = supervisorRepository.findByNameAndUniversity(
					req.getSupervisorName(), req.getSupervisorUniversity());
			if (supervisor.isPresent())
				su = supervisor.get();
			else {
				su = new Supervisor();
			}
			su.setName(req.getSupervisorName());
			su.setUniversity(req.getSupervisorUniversity());
			su.setEmail(req.getSupervisorMail());
			su.setGraduationDepartment(req.getSupervisorGraduationDepartment());
			su.setGraduationYear(req.getSupervisorGraduationYear());
			supervisorRepository.save(su);

			toBeUpdated.setCompany(c);
			toBeUpdated.setSupervisor(su);
			toBeUpdated.setStartDate(req.getStartDate());
			toBeUpdated.setEndDate(req.getEndDate());
			toBeUpdated.setType(req.getType());
			
			return internshipRepository.save(toBeUpdated);
		}
		return null;
	}

	public Internship deleteInternship(Long internshipId) {
		Optional<Internship> opt = internshipRepository.findById(internshipId);
		internshipRepository.deleteById(internshipId);

		return opt.get();
	}

}
