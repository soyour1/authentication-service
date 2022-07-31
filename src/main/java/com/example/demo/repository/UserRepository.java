package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
}
