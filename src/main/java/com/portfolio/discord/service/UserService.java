package com.portfolio.discord.service;

import java.util.Optional;

import com.portfolio.discord.entity.User;

public interface UserService {
	
	User findByUsername(String username);
	
	void register(User user);

	User login(String username, String password);
}
