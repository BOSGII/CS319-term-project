package com.bosgii.internshipmanagement.matcher;

import java.util.List;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;

public abstract class IMatcher {
    public boolean match(List<Internship> internships, List<Instructor> instructors){
        if(checkBoundary(internships, instructors)){
            performMatching(internships, instructors);
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

    abstract public void performMatching(List<Internship> internships, List<Instructor> instructors);
}
