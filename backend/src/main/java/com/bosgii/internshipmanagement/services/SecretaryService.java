package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Student;
import com.bosgii.internshipmanagement.entities.Company;
import com.bosgii.internshipmanagement.entities.Supervisor;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.repos.CompanyRepository;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.repos.SubmissionRepository;
import com.bosgii.internshipmanagement.repos.SupervisorRepository;
import com.bosgii.internshipmanagement.repos.TARepository;
import com.bosgii.internshipmanagement.requests.AddInternshipRequest;
import com.bosgii.internshipmanagement.requests.AddStudentRequest;

@Service
public class SecretaryService {

	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private TARepository tARepository;
	private InternshipRepository internshipRepository;
	private SubmissionRepository submissionRepository;
	private CompanyRepository companyRepository;
	private SupervisorRepository supervisorRepository;
	
	public SecretaryService(StudentRepository studentRepository, InstructorRepository instructorRepository,
			TARepository tARepository, InternshipRepository internshipRepository,
			SubmissionRepository submissionRepository, CompanyRepository companyRepository,
			SupervisorRepository supervisorRepository) {
		this.studentRepository = studentRepository;
		this.instructorRepository = instructorRepository;
		this.tARepository = tARepository;
		this.internshipRepository = internshipRepository;
		this.submissionRepository = submissionRepository;
		this.companyRepository = companyRepository;
		this.supervisorRepository = supervisorRepository;
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Long id) {
		Optional<Student> s = studentRepository.findById(id);
		if(s.isEmpty()) {
			return null;
		}
		return s.get();
	}

	public Student addStudentIfNotExists(AddStudentRequest newStudentRequest) {
		// check if the student exists
		Optional<Student> student = studentRepository.findById(newStudentRequest.getId());
		if(student.isPresent())
			return student.get();
		
		Student addedStudent = new Student();
		addedStudent.setId(newStudentRequest.getId());
		addedStudent.setFullName(newStudentRequest.getFullName());
		addedStudent.setDepartment(newStudentRequest.getDepartment());
		addedStudent.setMail(newStudentRequest.getMail());
		addedStudent.setRole("Student");
		return studentRepository.save(addedStudent);
	}

	public List<Internship> getInternshipsByStudentId(Long studentId) {
		// check if a student with given Id exists
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isEmpty())
			return null;
		
		return internshipRepository.getInternshipsByStudentId(studentId);
	}

	public Internship addInternshipInfoIfNotExist(Long studentId, AddInternshipRequest newInternshipRequest) {
		// check if the internship of given type already exists
		InternshipType type = newInternshipRequest.getType();
		List<Internship> internshipsOfTheStudent = internshipRepository.getInternshipsByStudentId(studentId);
		for(Internship i : internshipsOfTheStudent) {
			if(i.getType() == type) {
				return i;
			}
		}
		
		// check if the company already exists
		Company c;
		Optional<Company> company = companyRepository.findByNameAndEmail(newInternshipRequest.getCompanyName(), newInternshipRequest.getCompanyEmail());
		if(company.isPresent())
			c = company.get();
		else {
			c = new Company();
			c.setName(newInternshipRequest.getCompanyName());
			c.setCompanyEmail(newInternshipRequest.getCompanyEmail());
			companyRepository.save(c);
		}
		
		// check if the supervisor already exists
		Supervisor s;
		Optional<Supervisor> supervisor = supervisorRepository.findByNameAndUniversity(newInternshipRequest.getSupervisorName(), newInternshipRequest.getSupervisorUniversity());
		if(supervisor.isPresent())
			s = supervisor.get();
		else {
			s = new Supervisor();
			s.setName(newInternshipRequest.getSupervisorName());
			s.setUniversity(newInternshipRequest.getSupervisorUniversity());
			s.setEmail(newInternshipRequest.getSupervisorMail());
			s.setGraduationDepartment(newInternshipRequest.getSupervisorGraduationDepartment());
			s.setGraduationYear(newInternshipRequest.getSupervisorGraduationYear());
			supervisorRepository.save(s);
		}
		
		Internship i = new Internship();
		i.setCompany(c);
		i.setSupervisor(s);
		
		// it is certain that a student with given id exists, otherwise secretary would not be able to enter the page
		i.setStudent(studentRepository.findById(studentId).get());
		
		i.setType(type);
		i.setStartDate(newInternshipRequest.getStartDate());
		i.setEndDate(newInternshipRequest.getEndDate());
		i.setNumOfDrafts(0);
		
		return internshipRepository.save(i);
	}

	public Internship getInternshipById(Long internshipId) {
		Optional<Internship> i = internshipRepository.findById(internshipId);
		if(i.isEmpty()) {
			return null;
		}
		return i.get();
	}

	public Internship getInternshipOfAStudentByType(Long studentId, InternshipType internshipType) {
		// check if student exists
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isEmpty())
			return null;
		
		List<Internship> internshipsOfTheStudent = internshipRepository.getInternshipsByStudentId(studentId);
		for(Internship i : internshipsOfTheStudent) {
			if(i.getType() == internshipType) {
				return i;
			}
		}
		
		return null;
	}
}
