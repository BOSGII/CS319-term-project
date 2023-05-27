package com.bosgii.internshipmanagement.requests;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class AskForRevisionRequest {
    MultipartFile feedback;
    ArrayList<String> comments;

    public MultipartFile getFeedback() {
        return feedback;
    }
    public void setFeedback(MultipartFile feedback) {
        this.feedback = feedback;
    }
    public ArrayList<String> getComments() {
        return comments;
    }
    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
}
