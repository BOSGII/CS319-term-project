package com.bosgii.internshipmanagement.entities;

import javax.persistence.*;

@Entity
@Table(name = "Evaluator")
public abstract class Evaluator extends User {
	String department;
	int numOfAssignedInternships;
	int completed;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getNumOfAssignedInternships() {
		return numOfAssignedInternships;
	}

	public void setNumOfAssignedInternships(int numOfAssignedInternships) {
		this.numOfAssignedInternships = numOfAssignedInternships;
	}

}
