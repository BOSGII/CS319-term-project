package com.bosgii.internshipmanagement.repos;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    default User getById(Long id) {
        Optional<User> optionalUser = findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new EntityNotFoundException("User with id " + id + " does not exist");
        }
    }
}
