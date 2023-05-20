package com.bosgii.internshipmanagement.matcher;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.InternshipRepository;

public class EqualMatcher extends IMatcher{

    public EqualMatcher(InternshipRepository internshipRepository, InstructorRepository instructorRepository ){
        super(internshipRepository, instructorRepository);
    }

    @Override
    public void performMatching(List<Internship> internships, List<Instructor> instructors) {
        // sort instructors array
        Collections.sort(instructors, new Comparator<Instructor>() {
            public int compare(Instructor i1, Instructor i2){
                return i1.getMaxNumOfInternships() - i2.getMaxNumOfInternships();
            }
        });

        int remainingInternships = internships.size();
        int remainingInstructors = instructors.size();
        int idx = 0;
        for (Instructor instructor : instructors){
            int toBeAssigned;
            if(instructor.getMaxNumOfInternships() < remainingInternships / remainingInstructors )
                toBeAssigned = instructor.getMaxNumOfInternships();
            else
                toBeAssigned = remainingInternships / remainingInstructors;

            remainingInternships -= toBeAssigned;
            remainingInstructors--;
            
            for(int i = idx; i < idx + toBeAssigned; i++)
                internships.get(i).setInstructor(instructor);

            instructor.setNumOfAssignedInternships(toBeAssigned);
            idx += toBeAssigned;
        }
    }
    
}
