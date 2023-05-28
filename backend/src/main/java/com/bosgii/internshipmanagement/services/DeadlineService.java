package com.bosgii.internshipmanagement.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.repos.InternshipRepository;

@Service
public class DeadlineService {

    private final InternshipService internshipService;
    private final InternshipRepository internshipRepository;

    public DeadlineService(InternshipService internshipService, InternshipRepository internshipRepository) {
        this.internshipService = internshipService;
        this.internshipRepository = internshipRepository;
    }

    public void setInitialDeadline(Date deadline) {
        List<Internship> allInternships = internshipService.getAllInternships(Optional.empty(), Optional.empty());
        for (Internship singleInternship: allInternships) {
            singleInternship.setDeadline(deadline);
        }
        internshipRepository.saveAll(allInternships);

    }

    public void updateDeadlineByTwoWeeks(Internship internship) {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, 14);
        internship.setDeadline(calender.getTime());
        internshipRepository.save(internship);
    }

    public void extendDeadline(Long internshipId, Date deadline) {
        Optional<Internship> optional = internshipService.getOneInternshipById(internshipId);
        
        if (optional.isPresent()) {
            Internship internship = optional.get();
            internship.setDeadline(deadline);
            internshipRepository.save(internship);
            return;
        }
    }
}
