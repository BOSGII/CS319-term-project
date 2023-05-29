package com.bosgii.internshipmanagement.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bosgii.internshipmanagement.entities.User;
import com.bosgii.internshipmanagement.repos.UserRepository;

@Service
public class CurrentUserService implements UserDetailsService {

    private final UserRepository repository;

    public CurrentUserService(
            UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = repository.findById(Long.valueOf(username));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            final CurrentUser currentUser = new CurrentUser();
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());

            return currentUser;
        } else {
            throw new UsernameNotFoundException("Failed to find user with username: " + username);
        }
    }
}
