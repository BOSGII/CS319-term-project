package com.bosgii.internshipmanagement.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Instructor")
public class Instructor extends Evaluator{
    int maxNumOfInternships;
    
        public Instructor() {
            
        }
    
    public int getMaxNumOfInternships() {
        return maxNumOfInternships;
    }

    public void setMaxNumOfInternships(int maxNumOfInternships) {
        this.maxNumOfInternships = maxNumOfInternships;
    }
	
}
