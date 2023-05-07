package com.bosgii.internshipmanagement.requests;

public class AddInstructorRequest {
    String fullName;
    String mail;
    String department;
    int maxNumOfSubmissions;

	public String getFullName() {
        return fullName;
    }
    public String getMail() {
        return mail;
    }
	public String getDepartment() {
        return department;
    }
    public int getMaxNumOfSubmissions() {
        return maxNumOfSubmissions;
    }
}
