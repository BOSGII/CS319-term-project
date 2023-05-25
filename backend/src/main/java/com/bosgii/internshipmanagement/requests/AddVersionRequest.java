package com.bosgii.internshipmanagement.requests;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class AddVersionRequest {
    MultipartFile report;
    ArrayList<String> replies;
    
    public MultipartFile getReport() {
        return report;
    }
    public void setReport(MultipartFile report) {
        this.report = report;
    }
    public ArrayList<String> getReplies() {
        return replies;
    }
    public void setReplies(ArrayList<String> replies) {
        this.replies = replies;
    }
}
