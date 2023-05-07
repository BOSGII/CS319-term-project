package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Company;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Student;
import com.bosgii.internshipmanagement.entities.Supervisor;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.repos.CompanyRepository;
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
	
	public InternshipService(InternshipRepository internshipRepository, StudentRepository studentRepository,
			CompanyRepository companyRepository, SupervisorRepository supervisorRepository) {
		this.internshipRepository = internshipRepository;
		this.studentRepository = studentRepository;
		this.companyRepository = companyRepository;
		this.supervisorRepository = supervisorRepository;
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
			st.setRole("Student");
			st.setDepartment(req.getStudentDepartment());
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
		
		i.setType(type);
		i.setStartDate(req.getStartDate());
		i.setEndDate(req.getEndDate());
		i.setNumOfVersions(0);

		return internshipRepository.save(i);
	}

	public Internship changeInternship(Long internshipId, ChangeInternshipRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public Internship deleteInternship(Long internshipId) {
		// TODO Auto-generated method stub
		return null;
	}

}
