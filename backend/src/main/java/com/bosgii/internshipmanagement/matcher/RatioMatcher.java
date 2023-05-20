package com.bosgii.internshipmanagement.matcher;

import java.util.List;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;

public class RatioMatcher extends IMatcher{

    public RatioMatcher(InternshipRepository internshipRepository, InstructorRepository instructorRepository ){
        super(internshipRepository, instructorRepository);
    }

    @Override
    public void performMatching(List<Internship> internships, List<Instructor> instructors) {
        int numberOfInternships = internships.size();

        int maxAssignable = 0;
        for(Instructor i : instructors)
            maxAssignable += i.getMaxNumOfInternships();

        int idx = 0;
        for(Instructor instructor : instructors){
            int toBeAssigned = (int) Math.round(numberOfInternships * instructor.getMaxNumOfInternships() / (double)maxAssignable);
            for(int i = idx; i < idx + toBeAssigned; i++)
                internships.get(i).setInstructor(instructor);
            
            instructor.setNumOfAssignedInternships(toBeAssigned);
            idx += toBeAssigned;
        }
    }
    
}