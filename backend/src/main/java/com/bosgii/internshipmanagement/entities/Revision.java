package com.bosgii.internshipmanagement.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Revision")
@Data
public class Revision {
	@Id
	Long id;
	
	@ManyToOne
	Submission submission;
}
