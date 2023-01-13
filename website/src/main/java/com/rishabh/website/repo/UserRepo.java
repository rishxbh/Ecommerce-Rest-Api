package com.rishabh.website.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.website.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
