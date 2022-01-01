package com.example.t12_24.repository;

import com.example.t12_24.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByusername(String username);
}
