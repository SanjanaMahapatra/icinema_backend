package com.infy.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.auth.entity.User;

public interface AuthenticationRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByUsername(String userName);
}
