package com.portfolio.discord.repository;
import com.portfolio.discord.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
}
