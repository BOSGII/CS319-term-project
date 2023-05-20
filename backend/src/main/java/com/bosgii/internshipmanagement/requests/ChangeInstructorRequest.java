package com.bosgii.internshipmanagement.requests;

public class ChangeInstructorRequest {
    String fullName;
    String mail;
    String department;
    int maxNumOfInternships;

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
