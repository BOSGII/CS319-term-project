package com.bosgii.internshipmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Evaluator")
@Data
public abstract class Evaluator extends User {
	String department;
	int maxNumOfSubmissions;
	int completed;
}
