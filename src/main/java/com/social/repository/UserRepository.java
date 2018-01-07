package com.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
