package com.bosgii.internshipmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.enums.MatchType;
import com.bosgii.internshipmanagement.matcher.IMatcher;
import com.bosgii.internshipmanagement.matcher.MatcherFactory;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;

@Service
public class MatchService {
    private final MatcherFactory matcherFactory;
    private final InternshipService internshipService;
    private final InstructorService instructorService;

    public MatchService(InternshipService internshipService, InstructorService instructorService, InternshipRepository internshipRepository, InstructorRepository instructorRepository){
        matcherFactory = new MatcherFactory(internshipRepository, instructorRepository);
        this.internshipService = internshipService;
        this.instructorService = instructorService;
    }

    public Boolean matchInstructorsWithInternships(MatchType matchType) {
        IMatcher matcher = matcherFactory.createMatcher(matchType);
        List<Internship> internships = internshipService.getAllInternships(Optional.empty(), Optional.empty());
        List<Instructor> instructors = instructorService.getAllInstructors(Optional.empty());
        return matcher.match(internships, instructors);
    }
}
