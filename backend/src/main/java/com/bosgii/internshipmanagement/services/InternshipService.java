package com.bosgii.internshipmanagement.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Company;
import com.bosgii.internshipmanagement.entities.CompanyEvaluation;
import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Student;
import com.bosgii.internshipmanagement.entities.Supervisor;
import com.bosgii.internshipmanagement.enums.InternshipStatus;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;
import com.bosgii.internshipmanagement.exceptions.UserIdExistsException;
import com.bosgii.internshipmanagement.repos.CompanyEvaluationRepository;
import com.bosgii.internshipmanagement.repos.CompanyRepository;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.SupervisorRepository;
import com.bosgii.internshipmanagement.repos.UserRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.requests.AddCompanyEvaluationRequest;
import com.bosgii.internshipmanagement.requests.AddInternshipRequest;
import com.bosgii.internshipmanagement.requests.AssignRequest;
import com.bosgii.internshipmanagement.requests.ChangeInternshipRequest;
import com.bosgii.internshipmanagement.requests.FinalizeSubmissionRequest;
import com.bosgii.internshipmanagement.requests.GenerateFinalPDFRequest;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class InternshipService {
	private final InternshipRepository internshipRepository;
	private final StudentRepository studentRepository;
	private final CompanyRepository companyRepository;
	private final SupervisorRepository supervisorRepository;
	private final InstructorRepository instructorRepository;
	private final CompanyEvaluationRepository companyEvaluationRepository;
	private final CompanyEvaluationFormService companyEvaluationFormService;
	private final FinalPDFRequestService finalPDFRequestService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public InternshipService(InternshipRepository internshipRepository, StudentRepository studentRepository,
			CompanyRepository companyRepository, SupervisorRepository supervisorRepository,
			InstructorRepository instructorRepository, CompanyEvaluationRepository companyEvaluationRepository,
			CompanyEvaluationFormService companyEvaluationFormService, FinalPDFRequestService finalPDFRequestService,
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.internshipRepository = internshipRepository;
		this.studentRepository = studentRepository;
		this.companyRepository = companyRepository;
		this.supervisorRepository = supervisorRepository;
		this.instructorRepository = instructorRepository;
		this.companyEvaluationRepository = companyEvaluationRepository;
		this.companyEvaluationFormService = companyEvaluationFormService;
		this.finalPDFRequestService = finalPDFRequestService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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

	public Optional<Internship> findInternshipByStudentIdAndType(Long studentId, InternshipType type) {
		return internshipRepository.findByStudentIdAndType(studentId, type);
	}

	public Internship addInternship(AddInternshipRequest req) throws InvalidMailAddressException, UserIdExistsException {
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
		if (student.isPresent()) {
			st = student.get();
		} else {
			st = new Student();
			if (userRepository.existsById(req.getStudentId())) {
				throw new UserIdExistsException(req.getStudentId());
			}
			st.setId(req.getStudentId());
			st.setFullName(req.getStudentFullName());
			st.setMail(req.getStudentMail());
			st.setRole("student");
			st.setDepartment(req.getStudentDepartment());
			st.setPassword(passwordEncoder.encode(st.getId().toString()));
			studentRepository.save(st);
			sendEmail(st.getMail());
		}

		// check if the company already exists
		Company c;
		Optional<Company> company = companyRepository.findByEmail(req.getCompanyEmail());
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
		Optional<Supervisor> supervisor = supervisorRepository.findByEmail(req.getSupervisorMail());
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

			// check if the company already exists
			Company c;
			Optional<Company> company = companyRepository.findByEmail(req.getCompanyEmail());
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
			Optional<Supervisor> supervisor = supervisorRepository.findByEmail(req.getSupervisorMail());
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

	public Internship assignToDifferentInstructor(Long internshipId, AssignRequest req) {
		Internship toBeUpdated = internshipRepository.findById(internshipId).get();
		if (req.getNewInstructorId() == null) {
			Instructor currentAssignedInstructor = toBeUpdated.getInstructor();
			if (currentAssignedInstructor != null) {
				currentAssignedInstructor
						.setNumOfAssignedInternships(currentAssignedInstructor.getNumOfAssignedInternships() - 1);
				instructorRepository.save(currentAssignedInstructor);
			}
			toBeUpdated.setInstructor(null);
		} else {
			Instructor currentAssignedInstructor = toBeUpdated.getInstructor();
			if (currentAssignedInstructor != null) {
				currentAssignedInstructor
						.setNumOfAssignedInternships(currentAssignedInstructor.getNumOfAssignedInternships() - 1);
				instructorRepository.save(currentAssignedInstructor);
			}
			Instructor newAssignedInstructor = instructorRepository.findById(req.getNewInstructorId()).get();
			newAssignedInstructor.setNumOfAssignedInternships(newAssignedInstructor.getNumOfAssignedInternships() + 1);
			instructorRepository.save(newAssignedInstructor);
			toBeUpdated.setInstructor(newAssignedInstructor);
		}

		return internshipRepository.save(toBeUpdated);
	}

	public void handleNewSubmission(Long internshipId) {
		Internship i = internshipRepository.findById(internshipId).get();
		i.setStatus(InternshipStatus.UNDER_EVALUATION);
		internshipRepository.save(i);
	}

	public void handleNewVersion(Internship i) {
		i.setNumOfVersions(i.getNumOfVersions() + 1);
		internshipRepository.save(i);
	}

	public Internship uploadCompanyEvaluation(Long internshipId, AddCompanyEvaluationRequest req) {
		Internship i = internshipRepository.findById(internshipId).get();
		Optional<CompanyEvaluation> ce = companyEvaluationRepository.findByInternshipId(internshipId);

		if (ce.isPresent()) {
			ce.get().setSupervisorGrade(req.getSupervisorGrade());
			companyEvaluationRepository.save(ce.get());

			// TODO: handle change file
			if (req.getFile() != null) {

			}
		} else {
			CompanyEvaluation newCe = new CompanyEvaluation();
			newCe.setInternship(i);
			newCe.setSupervisorGrade(req.getSupervisorGrade());
			companyEvaluationRepository.saveAndFlush(newCe);
			companyEvaluationFormService.addCompanyEvaluationFormToCompanyEvaluation(req.getFile(), newCe);
			i.setStatus(InternshipStatus.NOT_SUBMITTED);
		}

		return internshipRepository.saveAndFlush(i);
	}

	public void handleFinalizeInternship(Long internshipId, FinalizeSubmissionRequest req) {
		Internship i = internshipRepository.findById(internshipId).get();
		// map FinalizeSubmissionRequest to GenerateFinalPDFRequest
		GenerateFinalPDFRequest pdfReq = new GenerateFinalPDFRequest();
		CompanyEvaluation ce = companyEvaluationRepository.findByInternshipId(internshipId).get();
		pdfReq.setPointOfEmployer(ce.getSupervisorGrade());
		pdfReq.setIsWorkComp(req.getWorkDoneRelated().equals("YES") ? true : false);
		pdfReq.setIsSupervisorComp(req.getSupervisorRelated().equals("YES") ? true : false);
		if (req.getCompanyEvaluation().equals("Strongly Recommend")) {
			pdfReq.setEvaluationOfCompanyByInstructor(1);
		} else if (req.getCompanyEvaluation().equals("Satisfied")) {
			pdfReq.setEvaluationOfCompanyByInstructor(2);
		} else if (req.getCompanyEvaluation().equals("Not Recommending")) {
			pdfReq.setEvaluationOfCompanyByInstructor(3);
		}

		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(req.getGrade1());
		scores.add(req.getGrade2());
		scores.add(req.getGrade3());
		scores.add(req.getGrade4());
		scores.add(req.getGrade5());
		scores.add(req.getGrade6());
		scores.add(req.getGrade7());

		pdfReq.setScores(scores);

		ArrayList<ArrayList<Integer>> pages = new ArrayList<ArrayList<Integer>>();
		pages.add(csvToList(req.getPages1()));
		pages.add(csvToList(req.getPages2()));
		pages.add(csvToList(req.getPages3()));
		pages.add(csvToList(req.getPages4()));
		pages.add(csvToList(req.getPages5()));
		pages.add(csvToList(req.getPages6()));
		pages.add(csvToList(req.getPages7()));

		pdfReq.setPages(pages);

		finalPDFRequestService.GenerateFinalPdf(i, pdfReq);
	}

	private ArrayList<Integer> csvToList(String csv) {
		ArrayList<Integer> pages = new ArrayList<Integer>();

		if (csv.isEmpty())
			return pages;

		// Remove spaces between values
		csv = csv.replaceAll("\\s+", "");

		// Split the CSV string by comma
		List<String> values = Arrays.asList(csv.split(","));

		// Convert each value to an integer and add to the result list
		for (String value : values) {
			pages.add(Integer.parseInt(value));
		}

		return pages;
	}

	public void sendEmail(String recipientEmail) {
        // Outlook.com configuration
        String host = "smtp.office365.com";
        String port = "587";
        String username = "internshipbilkent@outlook.com";
        String password = "intern12345";

        // Email content
        String subject = "Internship Management";
        String body = "You are registered.";

        try {
            // Setup mail server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Create a session with authentication
            Session session = Session.getInstance(properties);
            MimeMessage message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set the email subject and body
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Email sent successfully!");
            } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
