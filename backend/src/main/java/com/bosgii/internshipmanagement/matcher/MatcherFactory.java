package com.bosgii.internshipmanagement.matcher;

import org.springframework.stereotype.Component;

import com.bosgii.internshipmanagement.enums.MatchType;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;

@Component
public class MatcherFactory {
    private final InternshipRepository internshipRepository;
    private final InstructorRepository instructorRepository;

    public MatcherFactory(InternshipRepository internshipRepository, InstructorRepository instructorRepository){
        this.internshipRepository = internshipRepository;
        this.instructorRepository = instructorRepository;
    }

    public IMatcher createMatcher(MatchType matchType){
        switch(matchType){
            case EQUAL:
                return new EqualMatcher(internshipRepository, instructorRepository);
            case RATIO:
                return new RatioMatcher(internshipRepository, instructorRepository);
            default:
                return null;
        }
    }
}
