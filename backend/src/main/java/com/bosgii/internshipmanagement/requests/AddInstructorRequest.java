package com.bosgii.internshipmanagement.requests;

public class AddInstructorRequest {
    Long id;
    String fullName;
    String mail;
    String department;
    int maxNumOfInternships;
    
    public Long getId() {
        return id;
    }
	public String getFullName() {
        return fullName;
    }
    public String getMail() {
        return mail;
    }
	public String getDepartment() {
        return department;
    }
    public int getMaxNumOfInternships() {
        return maxNumOfInternships;
    }
}
