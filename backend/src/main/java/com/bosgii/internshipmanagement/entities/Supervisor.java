package com.bosgii.internshipmanagement.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Supervisor")
public class Supervisor {
	@Id
	Long id;
	
	String name;
	String email;
	Date graduationYear;
	String graduationDepartment;
	String university;
}
