package com.portfolio.discord.repository;

import java.util.Optional;

import com.portfolio.discord.entity.User;

public abstract class UserRepositoryImpl implements UserRepository {
    
	public Optional<User> findByUsernameorEmail(String username, String email){
		// i will check if username or email is in use here.. We can use database query or can check using JPA repository pre-defined methods as well
		return null;
	}

}
