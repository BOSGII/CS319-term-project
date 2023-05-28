package com.bosgii.internshipmanagement.requests;

import org.springframework.web.multipart.MultipartFile;

public class AddCompanyEvaluationRequest {
    MultipartFile file;
    int supervisorGrade;
    
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public int getSupervisorGrade() {
        return supervisorGrade;
    }
    public void setSupervisorGrade(int supervisorGrade) {
        this.supervisorGrade = supervisorGrade;
    }
}
