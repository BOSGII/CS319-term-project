package com.bosgii.internshipmanagement.entities;

import javax.persistence.*;

/* 
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
*/
@Entity
@Table(name = "Student")
public class Student extends User {
	private String department;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
