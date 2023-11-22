package com.digitalholics.iotheraphy.security.infrastructure.persistance.jpa.repositories;

import com.digitalholics.iotheraphy.security.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
