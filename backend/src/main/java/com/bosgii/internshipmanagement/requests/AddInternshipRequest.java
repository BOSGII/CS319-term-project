package com.bosgii.internshipmanagement.requests;

import java.util.Date;

import com.bosgii.internshipmanagement.enums.InternshipType;

public class AddInternshipRequest {
	// general info
	Date startDate;
	Date endDate;
	InternshipType type;
	
	// company info
	String companyName;
	String companyEmail;
	
	// supervisor info
	String supervisorName;
	String supervisorMail;
	Date supervisorGraduationYear;
	String supervisorGraduationDepartment;
	String supervisorUniversity;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public InternshipType getType() {
		return type;
	}
	public void setType(InternshipType type) {
		this.type = type;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getSupervisorMail() {
		return supervisorMail;
	}
	public void setSupervisorMail(String supervisorMail) {
		this.supervisorMail = supervisorMail;
	}
	public Date getSupervisorGraduationYear() {
		return supervisorGraduationYear;
	}
	public void setSupervisorGraduationYear(Date supervisorGraduationYear) {
		this.supervisorGraduationYear = supervisorGraduationYear;
	}
	public String getSupervisorGraduationDepartment() {
		return supervisorGraduationDepartment;
	}
	public void setSupervisorGraduationDepartment(String supervisorGraduationDepartment) {
		this.supervisorGraduationDepartment = supervisorGraduationDepartment;
	}
	public String getSupervisorUniversity() {
		return supervisorUniversity;
	}
	public void setSupervisorUniversity(String supervisorUniversity) {
		this.supervisorUniversity = supervisorUniversity;
	}
}
