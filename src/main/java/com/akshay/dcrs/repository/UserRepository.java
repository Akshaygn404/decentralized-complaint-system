package com.akshay.dcrs.repository;

import com.akshay.dcrs.model.User;
import com.akshay.dcrs.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByRole(Role role);
}