package com.bosgii.internshipmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.User;
import com.bosgii.internshipmanagement.repos.AdminRepository;
import com.bosgii.internshipmanagement.repos.InstructorRepository;
import com.bosgii.internshipmanagement.repos.SecretaryRepository;
import com.bosgii.internshipmanagement.repos.StudentRepository;
import com.bosgii.internshipmanagement.repos.TARepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TARepository tARepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public User findById(Long id) {
        User user = studentRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }

        user = secretaryRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }

        user = adminRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }

        user = tARepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }

        user = instructorRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
}
