package com.bosgii.internshipmanagement.entities;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Comment")
@Data
public class Comment {
	@Id
	Long id;
	
	@ManyToOne
	Revision revision;
	
	Date date;
	String message;
}
