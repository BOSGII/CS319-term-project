package com.bosgii.internshipmanagement.requests;

import com.bosgii.internshipmanagement.enums.SubmissionStatus;

public class ChangeSubmissionRequest {


    SubmissionStatus submissionStatus;

    public SubmissionStatus getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(SubmissionStatus submissionStatus) {
        this.submissionStatus = submissionStatus;
    }
}
