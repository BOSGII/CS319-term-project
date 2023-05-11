package com.bosgii.internshipmanagement.matcher;

import java.util.List;

import com.bosgii.internshipmanagement.entities.Instructor;
import com.bosgii.internshipmanagement.entities.Internship;

public interface IMatcher {
    Boolean match(List<Internship> internships, List<Instructor> instructors);
}
