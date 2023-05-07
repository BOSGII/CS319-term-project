package com.bosgii.internshipmanagement.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Instructor")
public class Instructor extends Evaluator{

    public Instructor() {
        
    }
	
}
