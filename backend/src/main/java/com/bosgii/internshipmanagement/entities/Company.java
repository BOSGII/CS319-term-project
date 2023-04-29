package com.bosgii.internshipmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Company")
@Data
public class Company {
	@Id
	Long id;
	
	String name;
	String companyEmail;
}
