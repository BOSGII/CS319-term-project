package com.bosgii.internshipmanagement.entities;

import javax.persistence.*;

/* 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/
@Entity
@Table(name = "Company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String name;
	String email;

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

	public String getCompanyEmail() {
		return email;
	}

	public void setCompanyEmail(String companyEmail) {
		this.email = companyEmail;
	}
}
