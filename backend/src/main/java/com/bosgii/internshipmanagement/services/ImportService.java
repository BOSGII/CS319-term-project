package com.bosgii.internshipmanagement.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

@Service
public class ImportService {
    //private final InternshipService internshipService;
    private final StudentRepository studentRepository;
    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;
    private final SupervisorRepository supervisorRepository;

    
    public ImportService(StudentRepository studentRepository, InternshipRepository internshipRepository,
            CompanyRepository companyRepository, SupervisorRepository supervisorRepository) {
        this.studentRepository = studentRepository;
        this.internshipRepository = internshipRepository;
        this.companyRepository = companyRepository;
        this.supervisorRepository = supervisorRepository;
    }

    public List<Internship> importInternshipsFromExcelFile(MultipartFile file) {
        List<Internship> list = new ArrayList<Internship>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                ArrayList<Cell> rowInfo = new ArrayList<Cell>();
                for (Cell cell : row){
                    rowInfo.add(cell);
                }

                String typeString = rowInfo.get(0).getStringCellValue();
                InternshipType type = InternshipType.CS299;
                if (typeString.equals("CS399"))
                    type = InternshipType.CS399;
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
                
                //AddInternshipRequest req = new AddInternshipRequest(type, startDate, endDate, studentId, studentFullName, studentMail, studentDepartment, companyName, companyEmail, supervisorName, supervisorMail, supervisorGraduationYear, supervisorGraduationDepartment, supervisorUniversity)
                Internship internship = new Internship();
                internship.setType(type);
                internship.setStartDate(startDate);
                internship.setEndDate(endDate);

                Student st;
                Optional<Student> student = studentRepository.findById(studentId);
                if(student.isPresent()) {
                    st = student.get();
                }
                else {
                    st = new Student();
                    st.setId(studentId);
                    st.setFullName(studentFullName);
                    st.setMail(studentMail);
                    st.setRole("Student");
                    st.setDepartment(studentDepartment);
                    studentRepository.save(st);
                }
                
                internship.setStudent(st);

                Company company;
                Optional<Company> optCompany = companyRepository.findByNameAndEmail(companyName, companyEmail);
                if(optCompany.isPresent()) {
                    company = optCompany.get();
                }
                else {
                    company = new Company();
                    //company id: oto
                    company.setCompanyEmail(companyEmail);
                    company.setName(companyName);
                    companyRepository.save(company);
                }

                internship.setCompany(company);


                Supervisor supervisor;
                Optional<Supervisor> optSupervisor = supervisorRepository.findByNameAndUniversity(supervisorName, supervisorUniversity);
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
                    
                    supervisorRepository.save(supervisor);
                }

                internship.setSupervisor(supervisor);
                internship.setStatus(InternshipStatus.WAITING_FOR_COMPANY_APPROVAL);

                internshipRepository.saveAndFlush(internship);

                list.add(internship);
            }
            
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
