package com.portfolio.discord.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.discord.entity.User;
import com.portfolio.discord.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
    public void register(User user) {
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("Account already exists with same username/email.");
        } else {
            userRepository.save(user);
        }
    }
	
	
	@Override
	public User login(String username, String password) {
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
				return user;
		}
		return null;	}

}
