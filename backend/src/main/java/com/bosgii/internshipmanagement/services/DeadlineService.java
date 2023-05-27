package com.bosgii.internshipmanagement.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public void updateDeadlineByTwoWeeks(Internship internship) {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, 14);
        internship.setDeadline(calender.getTime());
    }

    public void extendDeadline(Long internshipId, Date deadline) {
        Optional<Internship> optional = internshipService.getOneInternshipById(internshipId);
        
        if (optional.isPresent()) {
            Internship internship = optional.get();
            internship.setDeadline(deadline);
            return;
        }
    }
}
