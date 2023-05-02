package com.bosgii.internshipmanagement.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Supervisor")
public class Supervisor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String name;
	String email;
	Date graduationYear;
	String graduationDepartment;
	String university;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(Date graduationYear) {
		this.graduationYear = graduationYear;
	}
	public String getGraduationDepartment() {
		return graduationDepartment;
	}
	public void setGraduationDepartment(String graduationDepartment) {
		this.graduationDepartment = graduationDepartment;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
}
