package com.bosgii.internshipmanagement.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//note: 2 versions for 2 different excel types xls and xlsx, one uses "poi.ss" other "poi.sl"

import com.bosgii.internshipmanagement.entities.Company;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.entities.Student;
import com.bosgii.internshipmanagement.entities.Supervisor;
import com.bosgii.internshipmanagement.enums.InternshipStatus;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.repos.CompanyRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.repos.SupervisorRepository;
//import com.bosgii.internshipmanagement.requests.AddInternshipRequest;
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
public class ImportService {
    //private final InternshipService internshipService;
    private final StudentRepository studentRepository;
    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;
    private final SupervisorRepository supervisorRepository;
    private final PasswordEncoder passwordEncoder;
    


    public ImportService(StudentRepository studentRepository, InternshipRepository internshipRepository,
            CompanyRepository companyRepository, SupervisorRepository supervisorRepository,
            PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.internshipRepository = internshipRepository;
        this.companyRepository = companyRepository;
        this.supervisorRepository = supervisorRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Internship> importInternshipsFromExcelFile(MultipartFile file) throws IllegalArgumentException{

        List<Internship> list = new ArrayList<Internship>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                ArrayList<Cell> rowInfo = new ArrayList<Cell>();
                for (Cell cell : row){
                    rowInfo.add(cell);
                }
                
                try{
                    
                    String typeString = rowInfo.get(0).getStringCellValue();
                    
                    InternshipType type;
                    if (typeString.equals("CS399"))
                        type = InternshipType.CS399;
                    else if (typeString.equals("CS299"))
                        type = InternshipType.CS299;
                    else
                        throw new IllegalArgumentException("Invalid Type");


                    Date startDate = rowInfo.get(1).getDateCellValue();
                    Date endDate = rowInfo.get(2).getDateCellValue();
                    
                    Long studentId = (long) rowInfo.get(3).getNumericCellValue();
                    String studentFullName = rowInfo.get(4).getStringCellValue();
                    String studentMail = rowInfo.get(5).getStringCellValue();
                    String studentDepartment = rowInfo.get(6).getStringCellValue();
                   
                    String companyName = rowInfo.get(7).getStringCellValue();
                    String companyEmail = rowInfo.get(8).getStringCellValue();
                    
                    String supervisorName = rowInfo.get(9).getStringCellValue();
                    String supervisorMail = rowInfo.get(10).getStringCellValue();
                    Date supervisorGraduationYear = rowInfo.get(11).getDateCellValue();
                    String supervisorGraduationDepartment = rowInfo.get(12).getStringCellValue();
                    String supervisorUniversity = rowInfo.get(13).getStringCellValue();

                   
                    Company company;
                    
                    Optional<Company> optCompany = companyRepository.findByEmail(companyEmail);
                   
                    if(optCompany.isPresent()) {
                        company = optCompany.get();
                    }
                    else {
                        
                        company = new Company();
                        //company id: auto
                        company.setCompanyEmail(companyEmail);
                        company.setName(companyName);
                        //companyRepository.save(company);

                    }
                    //System.out.println("C id: "+company.getId());
                    Supervisor supervisor;
                    Optional<Supervisor> optSupervisor = supervisorRepository.findByEmail(supervisorMail);
                    
                    if(optSupervisor.isPresent()) {
                        supervisor = optSupervisor.get();
                    }
                    else {
                        supervisor = new Supervisor();
                        //supervisor id: oto
                        supervisor.setEmail(supervisorMail);
                        supervisor.setName(supervisorName);
                        supervisor.setGraduationDepartment(supervisorGraduationDepartment);
                        supervisor.setGraduationYear(supervisorGraduationYear);
                        supervisor.setUniversity(supervisorUniversity);
                        
                        //supervisorRepository.save(supervisor);
                    }
                    Student student = new Student();
                    student.setDepartment(studentDepartment);
                    student.setMail(studentMail);
                    student.setRole("Student"); 
                    student.setFullName(studentFullName);
                    student.setId(studentId);
                    Internship internship = new Internship();
                    internship.setType(type);
                    internship.setStartDate(startDate);
                    internship.setEndDate(endDate);
                    internship.setCompany(company);
                    internship.setSupervisor(supervisor);
                    internship.setStatus(InternshipStatus.WAITING_FOR_COMPANY_APPROVAL);
                    internship.setStudent(student);

                    list.add(internship);

                }
                catch(Exception e){
                    throw new IllegalArgumentException("Problematic line detected on line " +(1 + row.getRowNum() )+ "!" );
                }

            }

            ArrayList<Student> receivers = new ArrayList<Student>();

            for (Internship internship: list) {
                Student student = internship.getStudent();
                Company company = internship.getCompany();
                Supervisor supervisor = internship.getSupervisor();


                Optional<Student> optStudent = studentRepository.findById(student.getId());
                if(!optStudent.isPresent()) {
                    //sendEmail(student.getMail());
                    receivers.add(student);
                    //new password
                    student.setPassword(passwordEncoder.encode(student.getId().toString()));
                }
                else{
                    student.setPassword(optStudent.get().getPassword());
                }
                studentRepository.saveAndFlush(student);
                    
                Optional<Company> optCompany = companyRepository.findByEmail(company.getCompanyEmail());
                if(optCompany.isPresent()) {
                    company.setId(optCompany.get().getId());
                }
                
                companyRepository.saveAndFlush(company);
                
                Optional<Supervisor> optSupervisor = supervisorRepository.findByEmail( supervisor.getEmail());
                if(optSupervisor.isPresent()) {
                    supervisor.setId(optSupervisor.get().getId());
                }
                supervisorRepository.saveAndFlush(supervisor);
                


                Optional<Internship> optInternship = internshipRepository.findByStudentIdAndType(student.getId(), internship.getType());

                if(optInternship.isPresent()){
                    Internship oldInternship = optInternship.get();
                    oldInternship.setCompany(company);
                    oldInternship.setStartDate(internship.getStartDate());
                    oldInternship.setEndDate(internship.getEndDate());
                    oldInternship.setSupervisor(supervisor);

                    internshipRepository.saveAndFlush(oldInternship);
                }
                else{
                    internshipRepository.saveAndFlush(internship);
                }

            }
            
            System.out.println(receivers.size());

            new Thread(new Runnable() {
                public void run() {
                    sendAllEmails(receivers);
                }
            }).start();



            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void sendAllEmails(ArrayList<Student> receivers) {
        // Outlook.com configuration
        String host = "smtp.office365.com";
        String port = "587";
        String username = "internshipbilkent@outlook.com";
        String password = "intern12345";

        for(Student st: receivers){
            String recipientEmail = st.getMail();
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
    /* 
  @Async
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
    }*/

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }


}
