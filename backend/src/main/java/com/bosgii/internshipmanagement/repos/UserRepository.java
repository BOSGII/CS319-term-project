package com.bosgii.internshipmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bosgii.internshipmanagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
