package com.bosgii.internshipmanagement.services;

import java.util.Date;
import java.util.List;

import com.bosgii.internshipmanagement.entities.Internship;

public class DeadlineService {

    private final InternshipService internshipService;

    public DeadlineService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    public void setInitialDeadline(Date deadline) {
        List<Internship> allInternships = internshipService.getAllInternships(null, null);
        for (Internship singleInternship: allInternships) {
            singleInternship.setDeadline(deadline);
        }
    }
    
}
