package com.bosgii.internshipmanagement.entities;

import javax.persistence.*;

/* 

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "AbstractUser")
public abstract class User {
	@Id
	Long id;

	String fullName;
	String mail;
	String role;
	String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return String.valueOf(id);
	}
}
