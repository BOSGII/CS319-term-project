package com.bosgii.internshipmanagement.services;

import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.repos.UserRepository;

@Service
public class AdminService {
	private UserRepository userRepository;

	public AdminService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}

