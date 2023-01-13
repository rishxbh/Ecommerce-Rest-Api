package com.rishabh.website.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabh.website.model.AuthenticationToken;
import com.rishabh.website.model.User;

public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {

	AuthenticationToken findByUser(User user);
	AuthenticationToken findByToken(String token);

}
