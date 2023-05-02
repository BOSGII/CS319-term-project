package com.bosgii.internshipmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Evaluator")
public abstract class Evaluator extends User {
	String department;
	int maxNumOfSubmissions;
	int completed;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getMaxNumOfSubmissions() {
		return maxNumOfSubmissions;
	}
	public void setMaxNumOfSubmissions(int maxNumOfSubmissions) {
		this.maxNumOfSubmissions = maxNumOfSubmissions;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
}
