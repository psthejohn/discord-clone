package com.portfolio.discord.repository;
import com.portfolio.discord.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    default Optional<User> findByUsernameOrEmail(String username, String email) {
        Optional<User> userByUsername = findByUsername(username);
        if (userByUsername.isPresent()) {
            return userByUsername;
        }
        return findByEmail(email);
    }
}
