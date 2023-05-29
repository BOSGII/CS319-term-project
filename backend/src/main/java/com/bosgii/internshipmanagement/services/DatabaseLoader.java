package com.bosgii.internshipmanagement.services;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Admin;
import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Secretary;
import com.bosgii.internshipmanagement.entities.Student;
import com.bosgii.internshipmanagement.entities.TA;
import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.SecretaryRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.repos.TARepository;
import com.bosgii.internshipmanagement.repos.AdminRepository;


@Service
public class DatabaseLoader {

    private final SecretaryRepository secretaryRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final InstructorRepository instructorRepository;
    private final TARepository taRepository;
    private final AdminRepository adminRepository;

    public DatabaseLoader(StudentRepository studentRepository, PasswordEncoder passwordEncoder,
            SecretaryRepository secretaryRepository, InstructorRepository instructorRepository,
            TARepository taRepository, AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretaryRepository = secretaryRepository;
        this.instructorRepository = instructorRepository;
        this.taRepository = taRepository;
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    private void initDatabase() throws InvalidMailAddressException {
        Student student = new Student();
        student.setId(55555L);
        student.setFullName("Oguz");
        student.setMail("oguz@gma.com");
        student.setPassword(passwordEncoder.encode("555"));
        student.setRole("student");

        System.out.println("Student + " + student.getPassword());

        studentRepository.save(student);

        Secretary secretary = new Secretary();
        secretary.setId(11111L);
        secretary.setFullName("sekreter");
        secretary.setMail("sekreter@sek.com");
        secretary.setPassword(passwordEncoder.encode("111"));
        secretary.setRole("secretary");

        secretaryRepository.save(secretary);

        Admin admin = new Admin();
        admin.setId(22222L);
        admin.setFullName("admo");
        admin.setMail("admo@ad.com");
        admin.setPassword(passwordEncoder.encode("222"));
        admin.setRole("admin");

        adminRepository.save(admin);

        Instructor instructor = new Instructor();
        instructor.setId(12359L);
        instructor.setFullName("David");
        instructor.setMail("david@dav.com");
        instructor.setPassword(passwordEncoder.encode("111"));
        instructor.setRole("instructor");
        instructor.setDepartment("CS");
        instructor.setMaxNumOfInternships(20);

        instructorRepository.save(instructor);

        TA ta = new TA();
        ta.setId(1298L);
        ta.setFullName("asistan");
        ta.setMail("serkan@se.com");
        ta.setPassword(passwordEncoder.encode("111"));
        ta.setRole("ta");

        taRepository.save(ta);
    }
}