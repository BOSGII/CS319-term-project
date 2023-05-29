package com.bosgii.internshipmanagement.entities;

import java.util.regex.Pattern;

import com.bosgii.internshipmanagement.exceptions.InvalidMailAddressException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "AbstractUser")
public abstract class User {
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
	);
	
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
	public void setMail(String mail) throws InvalidMailAddressException {
		if (EMAIL_PATTERN.matcher(mail).matches())
			this.mail = mail;
		else
			throw new InvalidMailAddressException(mail);
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
