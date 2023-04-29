package com.bosgii.internshipmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="User")
@Data
public abstract class User {
	@Id
	Long id;
	
	String fullName;
	String mail;
	String role;
}
