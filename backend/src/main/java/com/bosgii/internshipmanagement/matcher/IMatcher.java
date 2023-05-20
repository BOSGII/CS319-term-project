package com.bosgii.internshipmanagement.matcher;

import java.util.List;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;

public abstract class IMatcher {
    private final InternshipRepository internshipRepository;
    private final InstructorRepository instructorRepository;

    public IMatcher(InternshipRepository internshipRepository, InstructorRepository instructorRepository){
        this.internshipRepository = internshipRepository;
        this.instructorRepository = instructorRepository;
    }

    public boolean match(List<Internship> internships, List<Instructor> instructors){
        if(checkBoundary(internships, instructors)){
            performMatching(internships, instructors);
            updateDatabase(internships, instructors);
            return true;
        }
        return false;
    }

    private boolean checkBoundary(List<Internship> internships, List<Instructor> instructors){
        int maxAssignable = 0;

        for(Instructor i : instructors)
            maxAssignable += i.getMaxNumOfInternships();

        return internships.size() <= maxAssignable;
    }

    private void updateDatabase(List<Internship> internships, List<Instructor> instructors) {
        internshipRepository.saveAllAndFlush(internships);
        instructorRepository.saveAllAndFlush(instructors);
    }

    abstract public void performMatching(List<Internship> internships, List<Instructor> instructors);
}
