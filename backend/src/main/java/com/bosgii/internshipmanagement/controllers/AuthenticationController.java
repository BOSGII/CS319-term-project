package com.bosgii.internshipmanagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bosgii.internshipmanagement.dto.ResponseDTO;
import com.bosgii.internshipmanagement.dto.UserDTO;
import com.bosgii.internshipmanagement.session.SessionRegistry;

import com.bosgii.internshipmanagement.entities.User;

import com.bosgii.internshipmanagement.repos.UserRepository;

@CrossOrigin("http://localhost:3000/")
@RestController
public class AuthenticationController {
    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public SessionRegistry sessionRegistry;
    @Autowired
    public UserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword()));

        final String sessionId = sessionRegistry.registerSession(user.getId());
        Optional<User> userOptional = userRepo.findById(Long.valueOf(user.getId()));
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + user.getId());
        }
        String roleName = userOptional.get().getRole();
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);
        response.setRoleName(roleName);
        System.out.println(sessionId);
        return ResponseEntity.ok(response);
    }
}
