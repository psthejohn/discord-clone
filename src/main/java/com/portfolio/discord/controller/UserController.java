package com.portfolio.discord.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.portfolio.discord.entity.User;
import com.portfolio.discord.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/add")
	public User createUser(@RequestBody User user) {
		System.out.println("add user to database..........");
		return userRepository.save(user);
	}
	
	@GetMapping("/find/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable long userId) {
		System.out.println("finding user in database ..............");
		Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // User not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/all")
    public Page<User> listUsers(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        // Create page request for pagination
        Pageable pageable = PageRequest.of(page, size);
        // Retrieve page of users from UserRepository
        return userRepository.findAll(pageable);
    }
	

}