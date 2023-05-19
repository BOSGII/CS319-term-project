package com.bosgii.internshipmanagement.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.metamodel.model.domain.internal.ArrayTupleType;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.enums.InternshipType;
import com.bosgii.internshipmanagement.requests.AddInternshipRequest;

@Service
public class ImportService {
    private final InternshipService internshipService;

    public ImportService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    public List<Internship> importInternshipsFromExcelFile(MultipartFile file) {
        List<Internship> list = new ArrayList<Internship>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = (Sheet) workbook.getSheetAt(0);

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
                
                AddInternshipRequest req = new AddInternshipRequest(type, startDate, endDate, studentId, studentFullName, studentMail, studentDepartment, companyName, companyEmail, supervisorName, supervisorMail, supervisorGraduationYear, supervisorGraduationDepartment, supervisorUniversity)
                list.add(internshipService.addInternship(req));
            }
            
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
