package com.example.hellotestcontainers.repository;

import com.example.hellotestcontainers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
